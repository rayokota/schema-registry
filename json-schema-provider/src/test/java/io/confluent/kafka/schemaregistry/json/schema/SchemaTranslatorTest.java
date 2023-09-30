/*
 * Copyright 2023 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.kafka.schemaregistry.json.schema;

import static io.confluent.kafka.schemaregistry.json.JsonSchema.DEFAULT_BASE_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.github.erosb.jsonsKema.IJsonValue;
import com.github.erosb.jsonsKema.JsonParseException;
import com.github.erosb.jsonsKema.JsonParser;
import com.github.erosb.jsonsKema.JsonValue;
import com.github.erosb.jsonsKema.Schema;
import com.github.erosb.jsonsKema.SchemaClient;
import com.github.erosb.jsonsKema.SchemaLoader;
import com.github.erosb.jsonsKema.SchemaLoaderConfig;
import com.github.erosb.jsonsKema.SchemaLoadingException;
import io.confluent.kafka.schemaregistry.json.JsonSchema;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class SchemaTranslatorTest {

  private static final String recordSchemaString = "{\"properties\": {\n"
      + "     \"a\": {\"const\": 5},\n"
      + "     \"b\": {\"const\": 5, \"type\": \"integer\"},\n"
      + "     \"c\": {\"type\": [\"string\", \"integer\"]},\n"
      + "     \"d\": {\"anyOf\": [{\"type\": \"string\"}, {\"type\": \"integer\"}]},\n"
      + "     \"null\": {\"type\": \"null\"},\n"
      + "     \"boolean\": {\"type\": \"boolean\"},\n"
      + "     \"number\": {\"type\": \"number\"},\n"
      + "     \"string\": {\"minLength\": 3, \"type\": \"string\", \"maxLength\": 5}\n"
      + "  },\n"
      + "  \"type\": \"object\",\n"
      + "  \"additionalProperties\": false\n"
      + "}";

  private static final JsonSchema recordSchema = new JsonSchema(recordSchemaString);

  private static final String arraySchemaString = "{\"type\": \"array\", \"items\": { \"type\": "
      + "\"string\" } }";

  private static final JsonSchema arraySchema = new JsonSchema(arraySchemaString);

  private static final String unionSchemaString = "{\n"
      + "  \"oneOf\": [\n"
      + "    { \"type\": \"string\", \"maxLength\": 5 },\n"
      + "    { \"type\": \"number\", \"minimum\": 0 }\n"
      + "  ]\n"
      + "}";

  private static final JsonSchema unionSchema = new JsonSchema(unionSchemaString);

  private static final String enumSchemaString = "{ \"type\": \"string\", \"enum\": [\"red\", "
      + "\"amber\", \"green\"] }";

  private static final JsonSchema enumSchema = new JsonSchema(enumSchemaString);

  @Test
  public void testAllSchemas() throws Exception {
    JSONArray testCases = new JSONArray(readFile("all-schemas.json"));
    //JSONArray testCases = new JSONArray(readFile("test-schemas.json"));
    for (int i = 0; i < testCases.length(); i++) {
      JSONObject testCase = (JSONObject) testCases.get(i);
      boolean refs = testCase.getBoolean("refs");
      boolean skip = testCase.getBoolean("skip");
      if (skip) {
        continue;
      }
      String sourceJson;
      try {
        sourceJson = testCase.getJSONObject("source_schema").toString();
      } catch (Exception e) {
        sourceJson = String.valueOf(testCase.getBoolean("source_schema"));
      }
      JsonSchema sourceJsonSchema = new JsonSchema(sourceJson);
      String targetJson;
      try {
        targetJson = testCase.getJSONObject("target_schema").toString();
      } catch (Exception e) {
        targetJson = String.valueOf(testCase.getBoolean("target_schema"));
      }
      JsonSchema targetJsonSchema = new JsonSchema(targetJson);

      SchemaLoaderConfig config = new SchemaLoaderConfig(new DummySchemaClient(), DEFAULT_BASE_URI);
      JsonValue json = new JsonParser(sourceJson).parse();
      Schema schema = new SchemaLoader(json, config).load();
      SchemaTranslator.SchemaContext ctx = schema.accept(new SchemaTranslator());
      ctx.close();
      JsonSchema resultJsonSchema = new JsonSchema(ctx.schema());
      if (refs) {
        // The JSON has refs, just check that the jsonNodes are equal
        assertEquals(targetJsonSchema.toJsonNode(), resultJsonSchema.toJsonNode());
      } else {
        JsonSchema expectedJsonSchema = new JsonSchema(targetJsonSchema.rawSchema());
        assertEquals(expectedJsonSchema.normalize(), resultJsonSchema.normalize());
        List<String> errs = targetJsonSchema.isBackwardCompatible(resultJsonSchema);
        assertTrue(errs.isEmpty());
      }
    }
  }

  @Test
  public void testRecordSchema() throws Exception {
    JsonValue schemaJson = new JsonParser(recordSchemaString).parse();
    Schema schema = new SchemaLoader(schemaJson).load();
    SchemaTranslator.SchemaContext ctx = schema.accept(new SchemaTranslator());
    assertEquals(new JsonSchema(recordSchema.rawSchema()).normalize(),
        new JsonSchema(ctx.schema()).normalize());
  }

  @Test
  public void testArraySchema() throws Exception {
    JsonValue schemaJson = new JsonParser(arraySchemaString).parse();
    Schema schema = new SchemaLoader(schemaJson).load();
    SchemaTranslator.SchemaContext ctx = schema.accept(new SchemaTranslator());
    assertEquals(new JsonSchema(arraySchema.rawSchema()).normalize(),
        new JsonSchema(ctx.schema()).normalize());
  }

  @Test
  public void testUnionSchema() throws Exception {
    JsonValue schemaJson = new JsonParser(unionSchemaString).parse();
    Schema schema = new SchemaLoader(schemaJson).load();
    SchemaTranslator.SchemaContext ctx = schema.accept(new SchemaTranslator());
    assertEquals(new JsonSchema(unionSchema.rawSchema()).normalize(),
        new JsonSchema(ctx.schema()).normalize());
  }

  @Test
  public void testEnumSchema() throws Exception {
    JsonValue schemaJson = new JsonParser(enumSchemaString).parse();
    Schema schema = new SchemaLoader(schemaJson).load();
    SchemaTranslator.SchemaContext ctx = schema.accept(new SchemaTranslator());
    assertEquals(new JsonSchema(enumSchema.rawSchema()).normalize(),
        new JsonSchema(ctx.schema()).normalize());
  }

  public static class DummySchemaClient implements SchemaClient {

    private static final String schema = "{\n"
        + "  \"integer\": true,\n"
        + "  \"refToInteger\": true,\n"
        + "  \"$defs\": {\n"
        + "    \"orNull\": true\n"
        + "  }\n"
        + "}";

    public DummySchemaClient() {
    }

    @Override
    public InputStream get(URI uri) {
      return new ByteArrayInputStream(schema.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public IJsonValue getParsed(URI uri) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(get(uri)));
      String string = reader.lines().collect(Collectors.joining());
      try {
        return new JsonParser(string, uri).parse();
      } catch (JsonParseException ex) {
        throw new SchemaLoadingException("failed to parse json content returned from $uri", ex);
      }
    }
  }

  public static String readFile(String fileName) {
    ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    InputStream is = classLoader.getResourceAsStream(fileName);
    if (is != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
    return null;
  }
}

/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.confluent.kafka.schemaregistry.client.rest.entities.requests;

import static io.confluent.kafka.schemaregistry.client.rest.entities.Schema.VERSION_DESC;
import static io.confluent.kafka.schemaregistry.client.rest.entities.Schema.VERSION_EXAMPLE;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.confluent.kafka.schemaregistry.client.rest.entities.Metadata;
import io.confluent.kafka.schemaregistry.client.rest.entities.RuleSet;
import java.io.IOException;

import io.confluent.kafka.schemaregistry.client.rest.entities.Schema;
import io.confluent.kafka.schemaregistry.utils.JacksonMapper;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@io.swagger.v3.oas.annotations.media.Schema(description = "Schema register response")
public class RegisterSchemaResponse {

  private int id;
  private Integer version;
  private Metadata metadata;
  private RuleSet ruleSet;

  public static RegisterSchemaResponse fromJson(String json) throws IOException {
    return JacksonMapper.INSTANCE.readValue(json, RegisterSchemaResponse.class);
  }

  public RegisterSchemaResponse() {
  }

  public RegisterSchemaResponse(Schema schema) {
    this.id = schema.getId();
    this.version = schema.getVersion();
    this.metadata = schema.getMetadata();
    this.ruleSet = schema.getRuleSet();
  }

  @io.swagger.v3.oas.annotations.media.Schema(description = Schema.ID_DESC,
      example = Schema.ID_EXAMPLE)
  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(int id) {
    this.id = id;
  }

  @io.swagger.v3.oas.annotations.media.Schema(description = VERSION_DESC, example = VERSION_EXAMPLE)
  @JsonProperty("version")
  public Integer getVersion() {
    return this.version;
  }

  @JsonProperty("version")
  public void setVersion(Integer version) {
    this.version = version;
  }

  @io.swagger.v3.oas.annotations.media.Schema(description = Schema.METADATA_DESC)
  @JsonProperty("metadata")
  public Metadata getMetadata() {
    return this.metadata;
  }

  @JsonProperty("metadata")
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

  @io.swagger.v3.oas.annotations.media.Schema(description = Schema.RULESET_DESC)
  @JsonProperty("ruleSet")
  public RuleSet getRuleSet() {
    return this.ruleSet;
  }

  @JsonProperty("ruleSet")
  public void setRuleSet(RuleSet ruleSet) {
    this.ruleSet = ruleSet;
  }

  public String toJson() throws IOException {
    return JacksonMapper.INSTANCE.writeValueAsString(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterSchemaResponse that = (RegisterSchemaResponse) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}

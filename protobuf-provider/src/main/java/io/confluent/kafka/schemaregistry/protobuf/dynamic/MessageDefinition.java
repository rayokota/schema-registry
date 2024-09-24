/*
 * Copyright 2020 Confluent Inc.
 * Copyright 2015 protobuf-dynamic developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.confluent.kafka.schemaregistry.protobuf.dynamic;

import static io.confluent.kafka.schemaregistry.protobuf.dynamic.DynamicSchema.toMeta;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.DescriptorProtos.DescriptorProto;
import com.google.protobuf.DescriptorProtos.EnumDescriptorProto;
import com.google.protobuf.DescriptorProtos.ExtensionRangeOptions.Declaration;
import com.google.protobuf.DescriptorProtos.ExtensionRangeOptions.VerificationState;
import com.google.protobuf.DescriptorProtos.FeatureSet;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto;
import com.google.protobuf.DescriptorProtos.FieldDescriptorProto.Type;
import com.google.protobuf.DescriptorProtos.FieldOptions.CType;
import com.google.protobuf.DescriptorProtos.FieldOptions.JSType;
import com.google.protobuf.DescriptorProtos.OneofDescriptorProto;

import com.squareup.wire.schema.internal.parser.EnumElement;
import com.squareup.wire.schema.internal.parser.MessageElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import io.confluent.kafka.schemaregistry.protobuf.ProtobufSchema.ProtobufMeta;
import io.confluent.kafka.schemaregistry.protobuf.diff.Context;
import io.confluent.kafka.schemaregistry.protobuf.diff.Context.TypeElementInfo;
import io.confluent.protobuf.MetaProto;
import io.confluent.protobuf.MetaProto.Meta;
import java.util.List;
import java.util.Map;
import kotlin.Pair;

/**
 * MessageDefinition
 */
public class MessageDefinition {
  // --- public static ---

  public static Builder newBuilder(String msgTypeName) {
    return new Builder(msgTypeName);
  }

  // --- public ---

  public String toString() {
    return mMsgType.toString();
  }

  // --- package ---

  DescriptorProto getMessageType() {
    return mMsgType;
  }

  // --- private ---

  private MessageDefinition(DescriptorProto msgType) {
    mMsgType = msgType;
  }

  private DescriptorProto mMsgType;

  /**
   * MessageDefinition.Builder
   */
  public static class Builder {
    // --- public ---

    public String getName() {
      return mMsgTypeBuilder.getName();
    }

    public Builder addField(
        Context ctx,
        String type,
        String name,
        int num
    ) {
      return addField(ctx, FieldDefinition.newBuilder(name, num, type).build());
    }

    public Builder addField(Context ctx, FieldDefinition fd) {
      mMsgTypeBuilder.addField(ctx, fd.getFieldType());
      return this;
    }

    public OneofBuilder addOneof(String oneofName) {
      return addOneof(oneofName, null);
    }

    public OneofBuilder addOneof(String oneofName, FeatureSet features) {
      DescriptorProtos.OneofOptions.Builder optionsBuilder =
          DescriptorProtos.OneofOptions.newBuilder();
      if (features != null) {
        optionsBuilder.setFeatures(features);
      }
      mMsgTypeBuilder.addOneofDecl(OneofDescriptorProto.newBuilder()
          .setName(oneofName)
          .mergeOptions(optionsBuilder.build())
          .build());
      return new OneofBuilder(this, mOneofIndex++);
    }

    public boolean containsMessage(String name) {
      List<DescriptorProto> messages = mMsgTypeBuilder.getNestedTypeList();
      for (DescriptorProto message : messages) {
        if (message.getName().equals(name)) {
          return true;
        }
      }
      return false;
    }

    public Builder addMessageDefinition(MessageDefinition msgDef) {
      mMsgTypeBuilder.addNestedType(msgDef.getMessageType());
      return this;
    }

    public boolean containsEnum(String name) {
      List<EnumDescriptorProto> enums = mMsgTypeBuilder.getEnumTypeList();
      for (EnumDescriptorProto enumer : enums) {
        if (enumer.getName().equals(name)) {
          return true;
        }
      }
      return false;
    }

    public Builder addEnumDefinition(EnumDefinition enumDef) {
      mMsgTypeBuilder.addEnumType(enumDef.getEnumType());
      return this;
    }

    // Note: added
    public Builder addReservedName(String reservedName) {
      mMsgTypeBuilder.addReservedName(reservedName);
      return this;
    }

    // Note: added
    public Builder addReservedRange(int start, int end) {
      DescriptorProto.ReservedRange.Builder rangeBuilder =
          DescriptorProto.ReservedRange.newBuilder();
      rangeBuilder.setStart(start).setEnd(end);
      mMsgTypeBuilder.addReservedRange(rangeBuilder.build());
      return this;
    }

    public Builder addExtensionRange(
        int start,
        int end,
        List<Declaration> decls,
        FeatureSet features,
        VerificationState verification
    ) {
      DescriptorProto.ExtensionRange.Builder rangeBuilder =
          DescriptorProto.ExtensionRange.newBuilder();
      rangeBuilder.setStart(start).setEnd(end);
      DescriptorProtos.ExtensionRangeOptions.Builder optionsBuilder =
          DescriptorProtos.ExtensionRangeOptions.newBuilder();
      for (Declaration decl : decls) {
        optionsBuilder.addDeclaration(decl);
      }
      if (features != null) {
        optionsBuilder.setFeatures(features);
      }
      if (verification != null) {
        optionsBuilder.setVerification(verification);
      }
      rangeBuilder.mergeOptions(optionsBuilder.build());
      mMsgTypeBuilder.addExtensionRange(rangeBuilder.build());
      return this;
    }

    public Builder addExtendDefinition(Context ctx, FieldDefinition fd) {
      mMsgTypeBuilder.addExtension(ctx, fd.getFieldType());
      return this;
    }

    // Note: added
    public Builder setNoStandardDescriptorAccessor(boolean noStandardDescriptorAccessor) {
      DescriptorProtos.MessageOptions.Builder optionsBuilder =
          DescriptorProtos.MessageOptions.newBuilder();
      optionsBuilder.setNoStandardDescriptorAccessor(noStandardDescriptorAccessor);
      mMsgTypeBuilder.mergeOptions(optionsBuilder.build());
      return this;
    }

    // Note: added
    public Builder setDeprecated(boolean isDeprecated) {
      DescriptorProtos.MessageOptions.Builder optionsBuilder =
          DescriptorProtos.MessageOptions.newBuilder();
      optionsBuilder.setDeprecated(isDeprecated);
      mMsgTypeBuilder.mergeOptions(optionsBuilder.build());
      return this;
    }

    // Note: added
    public Builder setMapEntry(boolean mapEntry) {
      DescriptorProtos.MessageOptions.Builder optionsBuilder =
          DescriptorProtos.MessageOptions.newBuilder();
      optionsBuilder.setMapEntry(mapEntry);
      mMsgTypeBuilder.mergeOptions(optionsBuilder.build());
      return this;
    }

    public Builder setFeatures(FeatureSet features) {
      DescriptorProtos.MessageOptions.Builder optionsBuilder =
          DescriptorProtos.MessageOptions.newBuilder();
      optionsBuilder.setFeatures(features);
      mMsgTypeBuilder.mergeOptions(optionsBuilder.build());
      return this;
    }

    // Note: added
    public Builder setMeta(ProtobufMeta meta) {
      Meta m = toMeta(meta);
      if (m != null) {
        DescriptorProtos.MessageOptions.Builder optionsBuilder =
                DescriptorProtos.MessageOptions.newBuilder();
        optionsBuilder.setExtension(MetaProto.messageMeta, m);
        mMsgTypeBuilder.mergeOptions(optionsBuilder.build());
      }
      return this;
    }

    public MessageDefinition build() {
      return new MessageDefinition(mMsgTypeBuilder.build());
    }

    // --- private ---

    private Builder(String msgTypeName) {
      mMsgTypeBuilder = DescriptorProto.newBuilder();
      mMsgTypeBuilder.setName(msgTypeName);
    }

    private DescriptorProto.Builder mMsgTypeBuilder;
    private int mOneofIndex = 0;
  }

  /**
   * MessageDefinition.OneofBuilder
   */
  public static class OneofBuilder {
    // --- public ---

    public OneofBuilder addField(Context ctx, FieldDefinition fd) {
      mMsgBuilder.addField(ctx, fd);
      return this;
    }

    public MessageDefinition.Builder msgDefBuilder() {
      return mMsgBuilder;
    }

    public int getIdx() {
      return mIdx;
    }

    // --- private ---

    private OneofBuilder(MessageDefinition.Builder msgBuilder, int oneofIdx) {
      mMsgBuilder = msgBuilder;
      mIdx = oneofIdx;
    }

    private MessageDefinition.Builder mMsgBuilder;
    private int mIdx;
  }
}

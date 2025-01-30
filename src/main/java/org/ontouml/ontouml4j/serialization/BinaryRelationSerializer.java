package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.BinaryRelation;

public class BinaryRelationSerializer extends JsonSerializer<BinaryRelation> {

  public static void serializeFields(BinaryRelation relation, JsonGenerator jsonGen)
      throws IOException {
    jsonGen.writeStringField("type", "BinaryRelation");
    ClassifierSerializer.serializeFields(relation, jsonGen);
  }

  @Override
  public void serialize(
      BinaryRelation relation, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(relation, jsonGen);
    jsonGen.writeEndObject();
  }
}

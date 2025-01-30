package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.NaryRelation;

public class NaryRelationSerializer extends JsonSerializer<NaryRelation> {

  public static void serializeFields(NaryRelation relation, JsonGenerator jsonGen)
      throws IOException {
    jsonGen.writeStringField("type", "NaryRelation");
    ClassifierSerializer.serializeFields(relation, jsonGen);
  }

  @Override
  public void serialize(
      NaryRelation relation, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(relation, jsonGen);
    jsonGen.writeEndObject();
  }
}

package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.model.Class;
import org.ontouml.model.OntoumlElement;

public class ClassSerializer extends JsonSerializer<Class> {

  public static void serializeFields(Class clazz, JsonGenerator jsonGen) throws IOException {
    Serializer.writeNullableStringField("type", "Class", jsonGen);
    ClassifierSerializer.serializeFields(clazz, jsonGen);
    List<String> literalIds = clazz.getLiterals().stream().map(OntoumlElement::getId).toList();
    Serializer.writeEmptyableArrayField("literals", literalIds, jsonGen);
    Serializer.writeEmptyableArrayField("restrictedTo", clazz.getRestrictedTo(), jsonGen);
    Serializer.writeNullableBooleanField("isPowertype", clazz.isPowertype(), jsonGen);
    Serializer.writeNullableStringField("order", clazz.getOrder(), jsonGen);
  }

  @Override
  public void serialize(Class clazz, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(clazz, jsonGen);
    jsonGen.writeEndObject();
  }
}

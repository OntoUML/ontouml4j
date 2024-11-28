package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.model.Classifier;
import org.ontouml.model.Property;

public class ClassifierSerializer extends JsonSerializer<Classifier<?, ?>> {

  public static void serializeFields(Classifier<?, ?> item, JsonGenerator jsonGen)
      throws IOException {
    DecoratableSerializer.serializeFields(item, jsonGen);
    Serializer.writeNullableBooleanField("isAbstract", item.isAbstract(), jsonGen);
    List<String> propertiesIds = item.getProperties().stream().map(Property::getId).toList();
    Serializer.writeEmptyableArrayField("properties", propertiesIds, jsonGen);
  }

  @Override
  public void serialize(
      Classifier<?, ?> item, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(item, jsonGen);
    jsonGen.writeEndObject();
  }
}

package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.serialization.Serializer;

public class ClassViewSerializer extends JsonSerializer<ClassView> {
  public static void serializeFields(ClassView view, JsonGenerator jsonGen) throws IOException {
    Serializer.writeNullableStringField("type", "ClassView", jsonGen);
    ViewSerializer.serializeFields(view, jsonGen);
    Serializer.writeNullableStringField("rectangle", view.getRectangle().getId(), jsonGen);
  }

  @Override
  public void serialize(ClassView view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}

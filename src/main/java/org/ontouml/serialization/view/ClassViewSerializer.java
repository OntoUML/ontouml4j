package org.ontouml.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.view.ClassView;
import org.ontouml.serialization.Serializer;

public class ClassViewSerializer extends JsonSerializer<ClassView> {
  public static void serializeFields(ClassView view, JsonGenerator jsonGen) throws IOException {
    ViewSerializer.serializeFields(view, jsonGen);

    Serializer.writeNullableOjectField("rectangle", view.getRectangle(), jsonGen);
  }

  @Override
  public void serialize(ClassView view, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}

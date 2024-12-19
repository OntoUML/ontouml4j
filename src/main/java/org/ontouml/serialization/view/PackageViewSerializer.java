package org.ontouml.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.view.PackageView;
import org.ontouml.serialization.Serializer;

public class PackageViewSerializer extends JsonSerializer<PackageView> {
  public static void serializeFields(PackageView view, JsonGenerator jsonGen) throws IOException {
    ViewSerializer.serializeFields(view, jsonGen);
    Serializer.writeNullableOjectField("rectangle", view.getRectangle(), jsonGen);
  }

  @Override
  public void serialize(PackageView pkg, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(pkg, jsonGen);
    jsonGen.writeEndObject();
  }
}

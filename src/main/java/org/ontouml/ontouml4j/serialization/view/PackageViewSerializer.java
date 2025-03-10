package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.PackageView;
import org.ontouml.ontouml4j.serialization.Serializer;

public class PackageViewSerializer extends JsonSerializer<PackageView> {
  public static void serializeFields(PackageView view, JsonGenerator jsonGen) throws IOException {
    Serializer.writeNullableStringField("type", "PackageView", jsonGen);
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

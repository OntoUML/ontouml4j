package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.Decoratable;
import org.ontouml.model.stereotype.Stereotype;

public class DecoratableSerializer<S extends Stereotype> extends JsonSerializer<Decoratable<S>> {

  public static <S extends Stereotype> void serializeFields(
      Decoratable<S> item, JsonGenerator jsonGen) throws IOException {
    PackageableElementSerializer.serializeFields(item, jsonGen);
    Serializer.writeNullableBooleanField("isDerived", item.isDerived(), jsonGen);

    if (item.getCustomStereotype().isPresent()) {
      Serializer.writeNullableStringField(
          "stereotype", item.getCustomStereotype().get().toLowerCase(), jsonGen);
      return;
    }

    String ontoumlstereotype =
        item.getOntoumlStereotype().isEmpty()
            ? null
            : item.getOntoumlStereotype().get().toString().toLowerCase();
    Serializer.writeNullableOjectField("stereotype", ontoumlstereotype, jsonGen);
  }

  @Override
  public void serialize(Decoratable item, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(item, jsonGen);
    jsonGen.writeEndObject();
  }
}

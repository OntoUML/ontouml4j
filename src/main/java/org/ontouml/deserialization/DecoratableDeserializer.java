package org.ontouml.deserialization;

import static org.ontouml.deserialization.DeserializerUtils.deserializeNullableBooleanField;
import static org.ontouml.deserialization.DeserializerUtils.deserializeNullableStringField;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.Decoratable;

import java.io.IOException;

public class DecoratableDeserializer {
  public static void deserialize(Decoratable<?> decoratable, JsonNode root, ObjectCodec codec)
      throws IOException {
    String stereotype = deserializeNullableStringField(root, "stereotype");
    decoratable.setStereotype(stereotype);

    Boolean isDerived = deserializeNullableBooleanField(root, "isDerived");
    decoratable.setDerived(Boolean.TRUE.equals(isDerived));
  }
}

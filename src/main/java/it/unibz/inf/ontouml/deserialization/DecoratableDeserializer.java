package it.unibz.inf.ontouml.deserialization;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import it.unibz.inf.ontouml.model.Decoratable;

import java.io.IOException;

import static it.unibz.inf.ontouml.deserialization.DeserializerUtils.deserializeNullableStringField;


public class DecoratableDeserializer {

  public static void deserialize(Decoratable<?> decoratable, JsonNode root, ObjectCodec codec)
      throws IOException {
    String stereotype = deserializeNullableStringField(root, "stereotype");
    decoratable.setStereotype(stereotype);
  }
}

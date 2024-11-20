package org.ontouml.deserialization;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.Class;

import java.io.IOException;
import java.util.Arrays;

import static org.ontouml.deserialization.DeserializerUtils.*;

public class ClassDeserializer extends JsonDeserializer<Class> {

  @Override
  public Class deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Class clazz = new Class();

    OntoumlElementDeserializer.deserialize(clazz, root, codec);
    NamedElementDeserializer.deserialize(clazz, root, codec);
    DecoratableDeserializer.deserialize(clazz, root, codec);
    ModelElementDeserializer.deserialize(clazz, root, codec);
    ClassifierDeserializer.deserialize(clazz, root, codec);

    Boolean isPowertype = deserializeNullableBooleanField(root, "isPowertype");
    clazz.setPowertype(Boolean.TRUE.equals(isPowertype));

    String order = deserializeNullableStringField(root, "order");
    clazz.setOrder(order);

    String[] restrictedTo = deserializeNullableStringArrayField(root, "restrictedTo", codec);
    if (restrictedTo != null) {
      clazz.setRestrictedTo(Arrays.stream(restrictedTo).toList());
    }

    String[] literals = deserializeNullableStringArrayField(root, "literals", codec);
    if (literals != null) {
      clazz.setLiterals(Arrays.stream(literals).toList());
    }

    String[] properties = deserializeNullableStringArrayField(root, "properties", codec);
    if (properties != null) {
      clazz.setProperties(Arrays.stream(properties).toList());
    }

    return clazz;
  }
}

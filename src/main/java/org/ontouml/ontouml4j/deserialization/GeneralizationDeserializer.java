package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.Generalization;

public class GeneralizationDeserializer extends JsonDeserializer<Generalization> {

  @Override
  public Generalization deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Generalization gen = new Generalization();
    OntoumlElementDeserializer.deserialize(gen, root, codec);
    NamedElementDeserializer.deserialize(gen, root, codec);
    ModelElementDeserializer.deserialize(gen, root, codec);

    String general = DeserializerUtils.deserializeNullableStringField(root, "general");
    gen.setGeneralId(general);

    String specific = DeserializerUtils.deserializeNullableStringField(root, "specific");
    gen.setSpecificId(specific);

    return gen;
  }
}

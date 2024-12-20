package org.ontouml.deserialization;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.model.Relation;

public class RelationDeserializer {
  public static void deserialize(Relation relation, JsonNode root, ObjectCodec codec)
      throws IOException {

    OntoumlElementDeserializer.deserialize(relation, root, codec);
    NamedElementDeserializer.deserialize(relation, root, codec);
    ModelElementDeserializer.deserialize(relation, root, codec);
    DecoratableDeserializer.deserialize(relation, root, codec);
    ClassifierDeserializer.deserialize(relation, root, codec);
  }
}

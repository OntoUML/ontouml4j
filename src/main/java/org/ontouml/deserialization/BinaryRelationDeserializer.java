package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.BinaryRelation;

import java.io.IOException;

public class BinaryRelationDeserializer extends JsonDeserializer<BinaryRelation> {
  @Override
  public BinaryRelation deserialize(
      JsonParser parser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    BinaryRelation relation = new BinaryRelation();
    OntoumlElementDeserializer.deserialize(relation, root, codec);
    NamedElementDeserializer.deserialize(relation, root, codec);
    ModelElementDeserializer.deserialize(relation, root, codec);
    DecoratableDeserializer.deserialize(relation, root, codec);
    ClassifierDeserializer.deserialize(relation, root, codec);

    return relation;
  }
}

package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.BinaryRelation;

public class BinaryRelationDeserializer extends JsonDeserializer<BinaryRelation> {
  @Override
  public BinaryRelation deserialize(
      JsonParser parser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    BinaryRelation relation = new BinaryRelation();
    RelationDeserializer.deserialize(relation, root, codec);

    return relation;
  }
}

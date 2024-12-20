package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.model.NaryRelation;

public class NaryRelationDeserializer extends JsonDeserializer<NaryRelation> {

  @Override
  public NaryRelation deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    NaryRelation relation = new NaryRelation();
    RelationDeserializer.deserialize(relation, root, codec);

    return relation;
  }
}

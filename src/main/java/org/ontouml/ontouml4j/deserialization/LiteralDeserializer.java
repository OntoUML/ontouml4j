package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.Literal;

public class LiteralDeserializer extends JsonDeserializer<Literal> {

  @Override
  public Literal deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Literal literal = new Literal();
    OntoumlElementDeserializer.deserialize(literal, root, codec);
    NamedElementDeserializer.deserialize(literal, root, codec);
    ModelElementDeserializer.deserialize(literal, root, codec);

    return literal;
  }
}

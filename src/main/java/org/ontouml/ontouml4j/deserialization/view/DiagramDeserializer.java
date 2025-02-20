package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.deserialization.NamedElementDeserializer;
import org.ontouml.ontouml4j.model.view.Diagram;

public class DiagramDeserializer extends JsonDeserializer<Diagram> {
  @Override
  public Diagram deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Diagram diagram = new Diagram();

    NamedElementDeserializer.deserialize(diagram, root, codec);

    return diagram;
  }
}

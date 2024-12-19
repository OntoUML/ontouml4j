package org.ontouml.deserialization.shape;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.shape.Text;

public class TextDeserializer extends JsonDeserializer<Text> {
  @Override
  public Text deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Text text = new Text();
    RectangularShapeDeserializer.deserialize(text, root, codec);

    return text;
  }
}

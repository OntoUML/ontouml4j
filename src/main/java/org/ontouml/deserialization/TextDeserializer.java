package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.view.Text;

import java.io.IOException;

public class TextDeserializer extends JsonDeserializer<Text> {
  @Override
  public Text deserialize(JsonParser parser, DeserializationContext context) throws IOException {

    JsonNode root = parser.readValueAsTree();

    Text text = new Text();

    String value = DeserializerUtils.deserializeNullableStringField(root, "value");
    text.setValue(value);

    RectangularShapeDeserializer.deserialize(text, root);

    return text;
  }
}

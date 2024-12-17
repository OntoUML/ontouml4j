package org.ontouml.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.model.view.ClassView;
import org.ontouml.shape.Rectangle;

public class ClassViewDeserializer extends JsonDeserializer<ClassView> {

  @Override
  public ClassView deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    ClassView view = new ClassView();

    String id = root.get("id").asText();
    view.setId(id);

    String rectangle = root.get("rectangle").asText();
    view.setRectangle(new Rectangle(rectangle));
    System.out.println("Deserialized retangle id:" + rectangle);

    ViewDeserializer.deserialize(view, root, codec);

    return view;
  }
}

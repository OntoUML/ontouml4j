package it.unibz.inf.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.unibz.inf.ontouml.model.Class;
import it.unibz.inf.ontouml.view.ClassView;
import it.unibz.inf.ontouml.view.Rectangle;

import java.io.IOException;

import static it.unibz.inf.ontouml.deserialization.DeserializerUtils.deserializeObjectField;


public class ClassViewDeserializer extends JsonDeserializer<ClassView> {

  @Override
  public ClassView deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    ClassView view = new ClassView();

    String id = root.get("id").asText();
    view.setId(id);

    Class element = deserializeObjectField(root, "modelElement", Class.class, codec);
    view.setModelElement(element);

    Rectangle shape = deserializeObjectField(root, "shape", Rectangle.class, codec);
    view.setShape(shape);

    return view;
  }
}

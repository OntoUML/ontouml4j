package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.Package;
import org.ontouml.view.PackageView;
import org.ontouml.view.Rectangle;

import java.io.IOException;

import static org.ontouml.deserialization.DeserializerUtils.deserializeObjectField;

public class PackageViewDeserializer extends JsonDeserializer<PackageView> {

  @Override
  public PackageView deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    PackageView view = new PackageView();

    String id = root.get("id").asText();
    view.setId(id);

    Package element = DeserializerUtils.deserializeObjectField(root, "modelElement", Package.class, codec);
    view.setModelElement(element);

    Rectangle shape = DeserializerUtils.deserializeObjectField(root, "shape", Rectangle.class, codec);
    view.setShape(shape);

    return view;
  }
}

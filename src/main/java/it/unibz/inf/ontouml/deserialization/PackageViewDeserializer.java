package it.unibz.inf.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.unibz.inf.ontouml.model.Package;
import it.unibz.inf.ontouml.view.PackageView;
import it.unibz.inf.ontouml.view.Rectangle;

import java.io.IOException;

import static it.unibz.inf.ontouml.deserialization.DeserializerUtils.deserializeObjectField;

public class PackageViewDeserializer extends JsonDeserializer<PackageView> {

  @Override
  public PackageView deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    PackageView view = new PackageView();

    String id = root.get("id").asText();
    view.setId(id);

    Package element = deserializeObjectField(root, "modelElement", Package.class, codec);
    view.setModelElement(element);

    Rectangle shape = deserializeObjectField(root, "shape", Rectangle.class, codec);
    view.setShape(shape);

    return view;
  }
}

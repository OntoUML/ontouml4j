package org.ontouml.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.model.view.PackageView;
import org.ontouml.shape.Rectangle;

public class PackageViewDeserializer extends JsonDeserializer<PackageView> {
  @Override
  public PackageView deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    PackageView view = new PackageView();

    String rectangle = root.get("rectangle").asText();
    view.setRectangle(new Rectangle(rectangle));

    ViewDeserializer.deserialize(view, root, codec);

    return view;
  }
}

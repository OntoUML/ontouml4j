package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.deserialization.OntoumlElementDeserializer;
import org.ontouml.ontouml4j.model.view.View;

public class ViewDeserializer {
  public static void deserialize(View view, JsonNode root, ObjectCodec codec)
      throws IOException, JacksonException {
    String id = root.get("isViewOf").asText();
    view.setIsViewOfId(id);

    OntoumlElementDeserializer.deserialize(view, root, codec);
  }
}

package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.NoteView;
import org.ontouml.ontouml4j.shape.Text;

public class NoteViewDeserializer extends JsonDeserializer<NoteView> {
  @Override
  public NoteView deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    NoteView view = new NoteView();

    String textId = root.get("text").asText();
    view.setText(new Text(textId));

    ViewDeserializer.deserialize(view, root, codec);

    return view;
  }
}

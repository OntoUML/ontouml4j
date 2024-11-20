package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Note;

import java.io.IOException;

public class NoteDeserializer extends JsonDeserializer<Note> {
  @Override
  public Note deserialize(JsonParser parser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Note note = new Note();

    OntoumlElementDeserializer.deserialize(note, root, codec);
    NamedElementDeserializer.deserialize(note, root, codec);
    ModelElementDeserializer.deserialize(note, root, codec);

    JsonNode textNode = root.get("text");
    if (textNode != null) {
      MultilingualText text = textNode.traverse(codec).readValueAs(MultilingualText.class);
      note.setText(text);
      System.out.println("Deserialized text: " + text);
    }

    return note;
  }
}

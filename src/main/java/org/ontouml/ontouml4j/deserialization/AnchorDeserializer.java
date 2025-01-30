package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.Anchor;

public class AnchorDeserializer extends JsonDeserializer<Anchor> {
  @Override
  public Anchor deserialize(JsonParser parser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();
    Anchor anchor = new Anchor();

    OntoumlElementDeserializer.deserialize(anchor, root, codec);
    NamedElementDeserializer.deserialize(anchor, root, codec);
    ModelElementDeserializer.deserialize(anchor, root, codec);

    String noteId = DeserializerUtils.deserializeNullableStringField(root, "note");
    anchor.setNoteId(noteId);

    String elementId = DeserializerUtils.deserializeNullableStringField(root, "element");
    anchor.setElementId(elementId);

    return anchor;
  }
}

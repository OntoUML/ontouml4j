package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.Note;

public class NoteSerializer extends JsonSerializer<Note> {
  public static void serializeFields(Note note, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", "Note");
    PackageableElementSerializer.serializeFields(note, jsonGen);
    Serializer.writeNullableOjectField("text", note.getText(), jsonGen);
  }

  @Override
  public void serialize(Note note, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(note, jsonGen);
    jsonGen.writeEndObject();
  }
}

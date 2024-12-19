package org.ontouml.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.model.view.NoteView;
import org.ontouml.serialization.Serializer;

public class NoteViewSerializer extends JsonSerializer<NoteView> {
  public static void serializeFields(NoteView view, JsonGenerator jsonGen) throws IOException {
    ViewSerializer.serializeFields(view, jsonGen);
    Serializer.writeNullableStringField("text", view.getText().getId(), jsonGen);
  }

  @Override
  public void serialize(NoteView note, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(note, jsonGen);
    jsonGen.writeEndObject();
  }
}

package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.NoteView;
import org.ontouml.ontouml4j.serialization.Serializer;

public class NoteViewSerializer extends JsonSerializer<NoteView> {
  public static void serializeFields(NoteView view, JsonGenerator jsonGen) throws IOException {
    Serializer.writeNullableStringField("type", "NoteView", jsonGen);
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

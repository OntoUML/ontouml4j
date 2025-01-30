package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.model.*;

public class AnchorSerializer extends JsonSerializer<Anchor> {
  public static void serializeFields(Anchor anchor, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", "Anchor");
    PackageableElementSerializer.serializeFields(anchor, jsonGen);
    Note note = anchor.getNote();
    if (note != null) {
      jsonGen.writeStringField("note", note.getId());
    } else {
      jsonGen.writeNullField("note");
    }
    ModelElement element = anchor.getElement();
    if (element != null) {
      jsonGen.writeStringField("element", element.getId());
    } else {
      jsonGen.writeNullField("element");
    }
  }

  @Override
  public void serialize(Anchor anchor, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(anchor, jsonGen);
    jsonGen.writeEndObject();
  }
}

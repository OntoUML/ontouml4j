package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.ontouml.view.Diagram;

import java.io.IOException;


public class DiagramSerializer extends JsonSerializer<Diagram> {

  @Override
  public void serialize(Diagram diagram, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(diagram, jsonGen, provider);
    jsonGen.writeEndObject();
  }

  static void serializeFields(Diagram diagram, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    OntoumlElementSerializer.serializeFields(diagram, jsonGen);
    Serializer.writeNullableReferenceField("owner", diagram.getOwner(), jsonGen);
    jsonGen.writeObjectField("contents", diagram.getContents());
  }
}

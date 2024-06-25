package it.unibz.inf.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.unibz.inf.ontouml.view.Diagram;

import java.io.IOException;

import static it.unibz.inf.ontouml.serialization.Serializer.writeNullableReferenceField;


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
    writeNullableReferenceField("owner", diagram.getOwner(), jsonGen);
    jsonGen.writeObjectField("contents", diagram.getContents());
  }
}

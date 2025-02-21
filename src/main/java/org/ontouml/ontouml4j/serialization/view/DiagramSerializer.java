package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.OntoumlElement;
import org.ontouml.ontouml4j.model.view.Diagram;
import org.ontouml.ontouml4j.serialization.NamedElementSerializer;
import org.ontouml.ontouml4j.serialization.Serializer;

public class DiagramSerializer extends JsonSerializer<Diagram> {

  public static void serializeFields(Diagram diagram, JsonGenerator jsonGen) throws IOException {
    Serializer.writeNullableStringField("type", "Diagram", jsonGen);
    NamedElementSerializer.serializeFields(diagram, jsonGen);

    List<String> viewsIds = diagram.getViews().stream().map(OntoumlElement::getId).toList();
    ModelElement owner = diagram.getOwner();
    if (owner != null) {
      Serializer.writeNullableStringField("owner", owner.getId(), jsonGen);
    } else {
      Serializer.writeNullableStringField("owner", null, jsonGen);
    }
    Serializer.writeEmptyableArrayField("views", viewsIds, jsonGen);
  }

  @Override
  public void serialize(
      Diagram diagram, JsonGenerator jsonGen, SerializerProvider serializerProvider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(diagram, jsonGen);
    jsonGen.writeEndObject();
  }
}

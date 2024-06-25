package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.ontouml.view.ElementView;

import java.io.IOException;

public class DiagramElementSerializer extends JsonSerializer<ElementView<?, ?>> {

  @Override
  public void serialize(
      ElementView<?, ?> element, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(element, jsonGen);
    jsonGen.writeEndObject();
  }

  static void serializeFields(ElementView<?, ?> element, JsonGenerator jsonGen) throws IOException {
    ElementSerializer.serializeId(element, jsonGen);
    OntoumlElementSerializer.serializeType(element, jsonGen);
    Serializer.writeNullableReferenceField("modelElement", element.getModelElement(), jsonGen);
    jsonGen.writeObjectField("shape", element.getShape());
  }
}

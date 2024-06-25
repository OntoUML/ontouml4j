package it.unibz.inf.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import it.unibz.inf.ontouml.view.NodeView;

import java.io.IOException;

public class NodeViewSerializer extends JsonSerializer<NodeView> {

  @Override
  public void serialize(NodeView nodeView, JsonGenerator jsonGen, SerializerProvider provider)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(nodeView, jsonGen);
    jsonGen.writeEndObject();
  }

  static void serializeFields(NodeView nodeView, JsonGenerator jsonGen) throws IOException {
    DiagramElementSerializer.serializeFields(nodeView, jsonGen);
  }
}

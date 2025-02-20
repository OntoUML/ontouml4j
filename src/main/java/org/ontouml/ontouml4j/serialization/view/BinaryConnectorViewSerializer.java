package org.ontouml.ontouml4j.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import org.ontouml.ontouml4j.model.view.BinaryConnectorView;
import org.ontouml.ontouml4j.serialization.Serializer;

public class BinaryConnectorViewSerializer {

  public static void serializeFields(BinaryConnectorView view, JsonGenerator jsonGen)
      throws IOException {
    ViewSerializer.serializeFields(view, jsonGen);
    Serializer.writeNullableStringField("sourceView", view.getSourceView().getId(), jsonGen);
    Serializer.writeNullableStringField("targetView", view.getTargetView().getId(), jsonGen);
    Serializer.writeNullableStringField("path", view.getPath().getId(), jsonGen);
  }

  public static void serialize(BinaryConnectorView view, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStartObject();
    BinaryConnectorViewSerializer.serializeFields(view, jsonGen);
    jsonGen.writeEndObject();
  }
}

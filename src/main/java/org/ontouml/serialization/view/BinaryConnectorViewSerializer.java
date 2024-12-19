package org.ontouml.serialization.view;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import org.ontouml.model.view.BinaryConnectorView;
import org.ontouml.serialization.Serializer;

public class BinaryConnectorViewSerializer {
  public static void serialize(BinaryConnectorView view, JsonGenerator jsonGen) throws IOException {
    ViewSerializer.serializeFields(view, jsonGen);
    Serializer.writeNullableStringField("sourceView", view.getSourceView().getId(), jsonGen);
    Serializer.writeNullableStringField("targetView", view.getTargetView().getId(), jsonGen);
    Serializer.writeNullableStringField("path", view.getPath().getId(), jsonGen);
  }
}

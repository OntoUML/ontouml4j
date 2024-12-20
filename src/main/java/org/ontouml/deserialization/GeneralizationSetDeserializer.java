package org.ontouml.deserialization;

import static org.ontouml.deserialization.DeserializerUtils.*;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.ontouml.model.GeneralizationSet;

public class GeneralizationSetDeserializer extends JsonDeserializer<GeneralizationSet> {
  @Override
  public GeneralizationSet deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    GeneralizationSet gs = new GeneralizationSet();
    OntoumlElementDeserializer.deserialize(gs, root, codec);
    NamedElementDeserializer.deserialize(gs, root, codec);
    ModelElementDeserializer.deserialize(gs, root, codec);

    boolean isComplete = deserializeBooleanField(root, "isComplete");
    gs.setComplete(isComplete);

    boolean isDisjoint = deserializeBooleanField(root, "isDisjoint");
    gs.setDisjoint(isDisjoint);

    JsonNode categorizerNode = root.get("categorizer");
    if (categorizerNode != null) {
      String categorizerId = categorizerNode.traverse(codec).readValueAs(String.class);
      gs.setCategorizerId(categorizerId);
      System.out.println("Deserialized categorizer:" + categorizerId);
    }

    List<String> generalizations =
        List.of(deserializeNullableStringArrayField(root, "generalizations", codec));
    gs.setGeneralizationIds(generalizations);

    return gs;
  }
}

package it.unibz.inf.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import it.unibz.inf.ontouml.model.Class;
import it.unibz.inf.ontouml.model.Generalization;
import it.unibz.inf.ontouml.model.GeneralizationSet;

import java.io.IOException;
import java.util.List;

import static it.unibz.inf.ontouml.deserialization.DeserializerUtils.*;


public class GeneralizationSetDeserializer extends JsonDeserializer<GeneralizationSet> {

  @Override
  public GeneralizationSet deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    GeneralizationSet gs = new GeneralizationSet();
    ElementDeserializer.deserialize(gs, root, codec);
    ModelElementDeserializer.deserialize(gs, root, codec);

    boolean isComplete = deserializeBooleanField(root, "isComplete");
    gs.setComplete(isComplete);

    boolean isDisjoint = deserializeBooleanField(root, "isDisjoint");
    gs.setDisjoint(isDisjoint);

    Class categorizer = deserializeObjectField(root, "categorizer", Class.class, codec);
    gs.setCategorizer(categorizer);

    List<Generalization> generalizations =
        deserializeArrayField(root, "generalizations", Generalization.class, codec);
    gs.setGeneralizations(generalizations);

    return gs;
  }
}

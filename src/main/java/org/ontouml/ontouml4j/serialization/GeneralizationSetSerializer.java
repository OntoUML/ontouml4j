package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.GeneralizationSet;
import org.ontouml.ontouml4j.model.OntoumlElement;

public class GeneralizationSetSerializer extends JsonSerializer<GeneralizationSet> {
  static void serializeFields(GeneralizationSet generalizationSet, JsonGenerator jsonGen)
      throws IOException {
    jsonGen.writeStringField("type", "GeneralizationSet");
    PackageableElementSerializer.serializeFields(generalizationSet, jsonGen);

    Serializer.writeNullableStringField(
        "isDisjoint", String.valueOf(generalizationSet.isDisjoint()), jsonGen);
    Serializer.writeNullableStringField(
        "isComplete", String.valueOf(generalizationSet.isComplete()), jsonGen);
    List<String> generalizationIds =
        generalizationSet.getGeneralizations().stream().map(OntoumlElement::getId).toList();
    Serializer.writeEmptyableArrayField(
        "generalizations", generalizationIds, jsonGen);
    Optional<Class> categorizer = generalizationSet.getCategorizer();
    if (categorizer.isPresent()) {
      Serializer.writeNullableStringField(
          "categorizer", categorizer.get().getId(), jsonGen);
    } else {
      jsonGen.writeNullField("categorizer");
    }
  }

  @Override
  public void serialize(
      GeneralizationSet generalizationSet, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(generalizationSet, jsonGen);
    jsonGen.writeEndObject();
  }
}

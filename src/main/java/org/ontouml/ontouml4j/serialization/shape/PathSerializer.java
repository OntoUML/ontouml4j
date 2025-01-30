package org.ontouml.ontouml4j.serialization.shape;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.ontouml.ontouml4j.serialization.OntoumlElementSerializer;
import org.ontouml.ontouml4j.serialization.Serializer;
import org.ontouml.ontouml4j.shape.Path;

public class PathSerializer extends JsonSerializer<Path> {

  public static void serializeFields(Path path, JsonGenerator jsonGen) throws IOException {
    OntoumlElementSerializer.serializeFields(path, jsonGen);
    Serializer.writeEmptyableArrayField("points", path.getPoints(), jsonGen);
  }

  @Override
  public void serialize(Path path, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(path, jsonGen);
    jsonGen.writeEndObject();
  }
}

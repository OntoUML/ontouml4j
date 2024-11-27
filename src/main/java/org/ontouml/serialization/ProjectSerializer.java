package org.ontouml.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.ontouml.model.OntoumlElement;
import org.ontouml.model.Package;
import org.ontouml.model.Project;

public class ProjectSerializer extends JsonSerializer<Project> {
  public static void serializeFields(Project project, JsonGenerator jsonGen) throws IOException {
    NamedElementSerializer.serializeFields(project, jsonGen);
    jsonGen.writeStringField("type", "Project");
    Package root = project.getRoot();
    if (root != null) {
      jsonGen.writeStringField("root", root.getId());
    } else {
      jsonGen.writeNullField("root");
    }
    List<OntoumlElement> elements = project.getElements();
    jsonGen.writeArrayFieldStart("elements");
    jsonGen.writeEndArray();
  }

  @Override
  public void serialize(Project project, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(project, jsonGen);
    jsonGen.writeEndObject();
  }
}

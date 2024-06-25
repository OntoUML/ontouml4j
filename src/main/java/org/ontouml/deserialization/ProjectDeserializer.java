package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.Project;
import org.ontouml.model.Package;
import org.ontouml.view.Diagram;

import java.io.IOException;
import java.util.List;

import static org.ontouml.deserialization.DeserializerUtils.deserializeArrayField;
import static org.ontouml.deserialization.DeserializerUtils.deserializeObjectField;

public class ProjectDeserializer extends JsonDeserializer<Project> {

  @Override
  public Project deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    System.out.println("Deserializing project...");

    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Project project = new Project();

    ElementDeserializer.deserialize(project, root, codec);

    Package model = DeserializerUtils.deserializeObjectField(root, "model", Package.class, codec);
    project.setModel(model);

    List<Diagram> diagrams = DeserializerUtils.deserializeArrayField(root, "diagrams", Diagram.class, codec);
    project.setDiagrams(diagrams);

    try {
      ReferenceResolver.resolveReferences(project);
    } catch (Exception e) {
      throw new JsonParseException(parser, "Cannot deserialize project", e);
    }

    return project;
  }
}

package org.ontouml.ontouml4j.deserialization.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.model.view.Diagram;
import org.ontouml.ontouml4j.utils.ResourceGetter;

public class DiagramDeserializerTest {
  ObjectMapper mapper = new ObjectMapper();
  Diagram diagram;

  @BeforeEach
  void beforeEach() throws IOException {
    File jsonFile = ResourceGetter.getJsonFromDeserialization("diagram.allfields.ontouml.json");
    Project project = mapper.readValue(jsonFile, Project.class);
    Optional<Diagram> diagram = project.getElementById("diagram_1", Diagram.class);
    diagram.ifPresent(aView -> this.diagram = aView);
  }

  @Test
  void shouldDeserializeDiagram() throws IOException {}
}

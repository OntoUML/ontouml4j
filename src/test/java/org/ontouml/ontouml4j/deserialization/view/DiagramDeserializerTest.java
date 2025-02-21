package org.ontouml.ontouml4j.deserialization.view;

import static com.google.common.truth.Truth.assertThat;

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
  Project project;

  @BeforeEach
  void beforeEach() throws IOException {
    File jsonFile = ResourceGetter.getJsonFromDeserialization("diagram.allfields.ontouml.json");
    project = mapper.readValue(jsonFile, Project.class);
    Optional<Diagram> diagram = project.getElementById("diagram_1", Diagram.class);
    diagram.ifPresent(aView -> this.diagram = aView);
  }

  @Test
  void shouldDeserializeDiagram() throws IOException {
    assertThat(diagram.getId()).isEqualTo("diagram_1");
  }

  @Test
  void shouldDeserializeViews() {
    assertThat(diagram.getViews()).hasSize(2);

    assertThat(diagram.getViews().stream().toList().getFirst().getId()).isEqualTo("classview_1");
    assertThat(diagram.getViews().stream().toList().getFirst().getIsViewOf().getId())
        .isEqualTo("class_1");
  }
}

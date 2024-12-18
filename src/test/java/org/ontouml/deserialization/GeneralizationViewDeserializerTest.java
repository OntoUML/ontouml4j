package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Project;
import org.ontouml.model.view.GeneralizationView;
import org.ontouml.utils.ResourceGetter;

public class GeneralizationViewDeserializerTest {

  ObjectMapper mapper;
  Project project;
  GeneralizationView genView;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile =
        ResourceGetter.getJsonFromDeserialization("generalizationView.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
      Optional<GeneralizationView> optionalGeneralizationView =
          project.getElementById("genview_1", GeneralizationView.class);

      optionalGeneralizationView.ifPresent(value -> genView = value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    assertThat(genView.getId()).isEqualTo("genview_1");
  }

  @Test
  void shouldDeserializeType() {
    assertThat(genView.getType()).isEqualTo("GeneralizationView");
  }

  @Test
  void shouldDeserializeSourceView() {
    assertThat(genView.getSourceView().getId()).isEqualTo("classview_1");
  }

  @Test
  void shouldDeserializeTargetView() {
    assertThat(genView.getTargetView().getId()).isEqualTo("classview_2");
  }

  @Test
  void shouldDeserializePath() {
    assertThat(genView.getPath().getId()).isEqualTo("path_1");
    assertThat(genView.getPath().getPoints().size()).isEqualTo(2);
  }
}

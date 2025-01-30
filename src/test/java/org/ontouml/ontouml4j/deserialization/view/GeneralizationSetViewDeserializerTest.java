package org.ontouml.ontouml4j.deserialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.model.view.GeneralizationSetView;
import org.ontouml.ontouml4j.model.view.GeneralizationView;
import org.ontouml.ontouml4j.utils.ResourceGetter;

public class GeneralizationSetViewDeserializerTest {

  ObjectMapper mapper;
  Project project;
  GeneralizationSetView gensetView;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile = ResourceGetter.getJsonFromDeserialization("gensetview.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
      Optional<GeneralizationSetView> optionalGeneralizationView =
          project.getElementById("gensetview_1", GeneralizationSetView.class);

      optionalGeneralizationView.ifPresent(value -> gensetView = value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    assertThat(gensetView.getId()).isEqualTo("gensetview_1");
  }

  @Test
  void shouldDeserializeType() {
    assertThat(gensetView.getType()).isEqualTo("GeneralizationSetView");
  }

  @Test
  void shouldDeserializeGeneralizations() {
    assertThat(gensetView.getGeneralizations().size()).isEqualTo(2);

    GeneralizationView firstGen = gensetView.getGeneralizations().get(0);
    GeneralizationView secondGen = gensetView.getGeneralizations().get(1);

    assertThat(firstGen.getId()).isEqualTo("genview_1");
    assertThat(secondGen.getId()).isEqualTo("genview_2");
  }

  @Test
  void shouldDeserializeIsViewOf() {
    ModelElement viewOf = gensetView.getIsViewOf();

    assertThat(viewOf.getId()).isEqualTo("genset_1");
  }
}

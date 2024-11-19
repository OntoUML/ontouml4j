package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Project;
import org.ontouml.utils.ResourceGetter;

import java.io.File;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;


public class ProjectDeserializerTest {
  static ObjectMapper mapper;
  static ResourceGetter resourceGetter = new ResourceGetter();
  static Project project;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile = resourceGetter.getJsonFromDeserialization("project.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Test
  void shouldDeserializeId() {
    assertThat(project.getId()).isEqualTo("proj1");
  }

  @Test
  void shouldDeserializeName() {
    assertThat(project.getNameIn("en")).hasValue("My Project");
    assertThat(project.getNameIn("pt")).hasValue("Meu Projeto");
  }

  @Test
  void shouldDeserializeDescription() {
    assertThat(project.getDescriptionIn("en")).hasValue("The best conceptual modeling project.");
    assertThat(project.getDescriptionIn("it"))
            .hasValue("Il miglior progetto in modellazione concettuale.");
  }
}

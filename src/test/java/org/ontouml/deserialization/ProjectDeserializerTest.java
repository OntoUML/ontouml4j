package org.ontouml.deserialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.MultilingualText;
import org.ontouml.Project;
import org.ontouml.utils.ResourceGetter;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

class ProjectDeserializerTest {

  static ObjectMapper mapper;
  static ResourceGetter resourceGetter;
  static Project project;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    resourceGetter = new ResourceGetter();
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

//  @Test
//  void shouldDeserializeModel() {
//    Optional<Package> model = project.getModel();
//    assertThat(model).isPresent();
//    Truth.assertThat(model.get().getId()).isEqualTo("pk1");
//    Truth8.assertThat(model.get().getFirstName()).hasValue("Model");
//  }

//  @Test
//  void shouldDeserializeModelContents() {
//    Package model = project.getModel().orElse(new Package());
//    Stream<String> idStream = model.getContents().stream().map(Element::getId);
//    assertThat(idStream).containsExactly("c1", "c2", "r1", "g1", "gs1");
//  }

  @Test
  void shouldDeserializeDates() {
    assertThat(project.getCreatedDate()).isEqualTo(Date.from(Instant.parse("2024-09-03T00:00:00Z")));
    assertThat(project.getModifiedDate()).isEqualTo(Date.from(Instant.parse("2024-09-04T00:00:00Z")));
  }

  @Test
  void shouldDeserializeAlternativeNames() {
    MultilingualText alternativeNames = new MultilingualText();
    alternativeNames.putText("en", "Project Alternative Name");
    alternativeNames.putText("pt", "Nome alternativo do Projeto");
    assertThat(project.getAlternativeNames().getFirst().toString()).isEqualTo(alternativeNames.toString());
  }

  @Test
  void shouldDeserializeKeywords() {
    List<MultilingualText> keywords = project.getMetaProperties().getKeywords();

    assertThat(keywords.getFirst().getText("en").get()).isEqualTo("keyword1");
    assertThat(keywords.get(1).getText("en").get()).isEqualTo("keyword2");
  }

  @Test
  void shouldDeserializeEditorialNotes() {
    MultilingualText editorialNote = new MultilingualText("pt", "An editorial Note.");

    assertThat(project.getEditorialNotes().getFirst().toString()).isEqualTo(editorialNote.toString());
  }

  @Test
  void shouldDeserializeDiagrams() {
    assertThat(project.getDiagrams()).isEmpty();
  }
}

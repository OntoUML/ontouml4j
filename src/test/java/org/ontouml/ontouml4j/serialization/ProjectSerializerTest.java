package org.ontouml.ontouml4j.serialization;

import static com.google.common.truth.Truth.assertThat;
import static org.ontouml.ontouml4j.utils.BuilderUtils.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.*;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Package;

public class ProjectSerializerTest {
  Project project;
  JsonNode node;
  DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

  @BeforeEach
  void beforeEach() throws URISyntaxException, JsonProcessingException {
    var projectName = new MultilingualText(Map.of("en", "My Project", "pt", "Meu Projeto"));
    Date modifiedDate =
        Date.from(ZonedDateTime.parse("2024-09-04T00:00:00Z", formatter).toInstant());
    Resource creator =
        new Resource(new MultilingualText("Matheus Lenke"), new URI("https://google.com"));
    MultilingualText description =
        new MultilingualText(
            Map.of(
                "en",
                "The best conceptual modeling project.",
                "it",
                "Il miglior progetto in modellazione concettuale."));

    Map<String, Package> packages = createPackages();

    Map<String, Generalization> generalizations = createGeneralizations();

    Map<String, GeneralizationSet> generalizationSets = createGeneralizationSets();

    Map<String, Class> classes = createClasses();

    generalizations.forEach(
        (key, item) -> {
          Class general = classes.get("class_1");
          Class specific = classes.get("class_2");
          item.setGeneral(general);
          item.setSpecific(specific);
        });

    generalizationSets.forEach(
        (key, item) -> {
          item.setGeneralizations(generalizations.values());
        });

    var notes = createNotes();
    var anchors = createAnchors();

    anchors.forEach(
        (key, item) -> {
          item.setNote(notes.get("note_1"));
          item.setElement(classes.get("class_1"));
        });

    project =
        Project.builder()
            .id("project_1")
            .created(new Date())
            .name(projectName)
            .modified(modifiedDate)
            .description(description)
            .alternativeNames(List.of(new MultilingualText("Project first alternative name")))
            .classes(classes)
            .properties(createProperties())
            .generalizations(generalizations)
            .generalizationSets(generalizationSets)
            .literals(createLiterals())
            .relations(createRelations())
            .packages(packages)
            .notes(notes)
            .anchors(anchors)
            .creators(List.of(creator))
            .build();
    packages.forEach(
        (key, item) -> {
          item.setProjectContainer(project);
        });

    node = project.serialize();
  }

  @Test
  void shouldSerializeId() {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("project_1");
  }

  @Test
  void shouldSerializeType() {
    String type = node.get("type").asText();
    assertThat(type).isEqualTo("Project");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    JsonNode node = project.serialize();

    String namePt = node.get("name").get("pt").asText();
    String nameEn = node.get("name").get("en").asText();

    assertThat(namePt).contains("Meu Projeto");
    assertThat(nameEn).contains("My Project");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    String descriptionIt = node.get("description").get("it").asText();
    String descriptionEn = node.get("description").get("en").asText();

    assertThat(descriptionIt).isEqualTo("Il miglior progetto in modellazione concettuale.");
    assertThat(descriptionEn).isEqualTo("The best conceptual modeling project.");
  }

  @Test
  void shouldSerializeDesignedForTasks() throws JsonProcessingException, URISyntaxException {
    project
        .getMetaProperties()
        .setDesignedForTasks(
            List.of(new Resource(new MultilingualText("Task"), new URI("https://localhost:8080"))));
    node = project.serialize();

    JsonNode task = node.get("designedForTasks").get(0);
    String uri = task.get("uri").textValue();
    String name = task.get("name").get("en").textValue();

    assertThat(uri).isEqualTo("https://localhost:8080");
    assertThat(name).isEqualTo("Task");
  }
}

package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Project;
import org.ontouml.model.Resource;

public class ProjectSerializerTest {
  static Project project;
  static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
  ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  @BeforeAll
  static void beforeAll() throws IOException, ParseException, URISyntaxException {
    var projectName = new MultilingualText("My Project");
    projectName.putText("pt-br", "Meu Projeto");
    Date modifiedDate =
        Date.from(ZonedDateTime.parse("2024-09-04T00:00:00Z", formatter).toInstant());
    Resource creator =
        new Resource(new MultilingualText("Matheus Lenke"), new URI("https://google.com"));
    MultilingualText description = new MultilingualText("The best conceptual modeling project.");
    description.putText("it", "Il miglior progetto in modellazione concettuale.");
    project =
        Project.builder()
            .id("project_1")
            .created(new Date())
            .name(projectName)
            .modified(modifiedDate)
            .description(description)
            .alternativeNames(List.of(new MultilingualText("Project first alternative name")))
            .creators(List.of(creator))
            .build();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String json = mapper.writeValueAsString(project);
    assertThat(json).contains("\"id\" : \"project_1\"");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    String json = mapper.writeValueAsString(project);
    assertThat(json).contains("\"type\" : \"Project\"");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    String json = mapper.writeValueAsString(project);
    assertThat(json).contains("\"name\" : {");
    assertThat(json).contains("\"pt-br\" : \"Meu Projeto\"");
    assertThat(json).contains("\"en\" : \"My Project\"");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    String json = mapper.writeValueAsString(project);

    assertThat(json).contains("\"description\" : {");
    assertThat(json).contains("\"it\" : \"Il miglior progetto in modellazione concettuale.\"");
    assertThat(json).contains("\"en\" : \"The best conceptual modeling project.\"");
  }

  @Test
  void shouldSerializeDesignedForTasks() throws JsonProcessingException, URISyntaxException {
    project
        .getMetaProperties()
        .setDesignedForTasks(
            List.of(new Resource(new MultilingualText("Task"), new URI("https://localhost:8080"))));
    String json = mapper.writeValueAsString(project);

    assertThat(json).contains("\"description\" : {");
    assertThat(json).contains("\"it\" : \"Il miglior progetto in modellazione concettuale.\"");
    assertThat(json).contains("\"en\" : \"The best conceptual modeling project.\"");
  }
}

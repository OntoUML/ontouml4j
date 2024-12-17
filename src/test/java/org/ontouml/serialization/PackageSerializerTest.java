package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.*;
import org.ontouml.model.Package;

public class PackageSerializerTest {
  Project project;
  Package pkg;
  DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

  @BeforeEach
  void beforeEach() throws URISyntaxException {
    project = Project.builder().id("Project_1").build();
    Date createdDate =
        Date.from(ZonedDateTime.parse("2024-09-03T00:00:00Z", formatter).toInstant());
    Date modifiedDate =
        Date.from(ZonedDateTime.parse("2024-09-04T00:00:00Z", formatter).toInstant());

    List<PackageableElement> contents = new ArrayList<>();
    List<MultilingualText> editorialNotes = List.of(new MultilingualText("Editorial Note 1"));
    List<Resource> creators =
        List.of(
            new Resource(new MultilingualText("Matheus Lenke"), new URI("https://lenke.software")));
    pkg =
        Package.builder()
            .id("package_1")
            .name(new MultilingualText("My Package"))
            .description(new MultilingualText("My Project description."))
            .created(createdDate)
            .modified(modifiedDate)
            .customProperties(Map.of("myProperty", "My Value"))
            .contents(contents)
            .creators(creators)
            .editorialNotes(editorialNotes)
            .build();
    project.addPackage(pkg);
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    JsonNode node = pkg.serialize();

    String id = node.get("id").asText();

    assertThat(id).isEqualTo("package_1");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    JsonNode node = pkg.serialize();

    String type = node.get("type").asText();
    assertThat(type).isEqualTo("Package");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    pkg.addName("en", "My Reference Ontology");
    pkg.addName("es", "Mi ontología de referencia");

    JsonNode node = pkg.serialize();

    String nameEn = node.get("name").get("en").asText();
    String nameEs = node.get("name").get("es").asText();

    assertThat(nameEn).contains("My Reference Ontology");
    assertThat(nameEs).contains("Mi ontología de referencia");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    pkg.addDescription("en", "The ontology about all things that could exist.");
    pkg.addDescription("es", "La ontología sobre todas las cosas que podrían existir.");

    JsonNode node = pkg.serialize();

    String descriptionEn = node.get("description").get("en").asText();
    String descriptionEs = node.get("description").get("es").asText();

    assertThat(descriptionEn).contains("The ontology about all things that could exist.");
    assertThat(descriptionEs).contains("La ontología sobre todas las cosas que podrían existir.");
  }

  @Test
  void shouldSerializeEmptyDescriptionAsNull() throws JsonProcessingException {
    pkg.setDescription(null);

    JsonNode node = pkg.serialize();

    String description = node.get("description").textValue();

    assertThat(description).isNull();
  }

  @Test
  void shouldSerializeCustomProperties() throws JsonProcessingException {
    pkg.setCustomProperties(Map.of("acronym", "UFO-X", "numberOfContributors", 100));

    JsonNode node = pkg.serialize();
    String numberOfContributors = node.get("customProperties").get("numberOfContributors").asText();
    String acronym = node.get("customProperties").get("acronym").asText();

    assertThat(numberOfContributors).isEqualTo("100");
    assertThat(acronym).isEqualTo("UFO-X");
  }

  @Test
  void shouldSerializeEmptyCustomPropertiesAsNull() throws JsonProcessingException {
    pkg.setCustomProperties(null);

    JsonNode node = pkg.serialize();

    String customProperties = node.get("customProperties").textValue();

    assertThat(customProperties).isNull();
  }

  @Test
  void shouldSerializeContents() throws JsonProcessingException {
    pkg.createClass("c1", "Person", (String) null);

    JsonNode node = pkg.serialize();

    List<String> contents =
        new ObjectMapper()
            .readValue(node.get("contents").toString(), new TypeReference<List<String>>() {});
    assertThat(contents).hasSize(1);
    assertThat(contents.getFirst()).isEqualTo("c1");
  }

  @Test
  void shouldSerializeEmptyContentsAsNull() throws JsonProcessingException {
    pkg.setContents();

    JsonNode node = pkg.serialize();
    List<String> contents =
        new ObjectMapper()
            .readValue(node.get("contents").toString(), new TypeReference<List<String>>() {});

    assertThat(contents).isEmpty();
  }
}

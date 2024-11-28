package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Package;
import org.ontouml.model.Project;
import org.ontouml.utils.BuilderUtils;

public class PackageSerializerTest {
  static Project project;
  static Package pkg;
  ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  @BeforeAll
  static void beforeAll() throws IOException, ParseException, URISyntaxException {
    project = BuilderUtils.createProject();
    Optional<Package> pkg1 = project.getElementById("package_1", Package.class);
    pkg1.ifPresent(p -> pkg = p);
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String json = mapper.writeValueAsString(pkg);
    assertThat(json).contains("\"id\" : \"package_1\"");
  }

  @Test
  void shouldSerializeType() throws JsonProcessingException {
    String json = mapper.writeValueAsString(pkg);
    assertThat(json).contains("\"type\" : \"Package\"");
  }

  @Test
  void shouldSerializeName() throws JsonProcessingException {
    pkg.addName("en", "My Reference Ontology");
    pkg.addName("es", "Mi ontología de referencia");

    String json = mapper.writeValueAsString(pkg);

    assertThat(json).contains("\"name\" : {");
    assertThat(json).contains("\"en\" : \"My Reference Ontology\"");
    assertThat(json).contains("\"es\" : \"Mi ontología de referencia\"");
  }

  @Test
  void shouldSerializeDescription() throws JsonProcessingException {
    pkg.addDescription("en", "The ontology about all things that could exist.");
    pkg.addDescription("es", "La ontología sobre todas las cosas que podrían existir.");

    String json = mapper.writeValueAsString(pkg);

    assertThat(json).contains("\"description\" : {");
    assertThat(json).contains("\"en\" : \"The ontology about all things that could exist.\"");
    assertThat(json)
        .contains("\"es\" : \"La ontología sobre todas las cosas que podrían existir.\"");
  }

  @Test
  void shouldSerializeEmptyDescriptionAsNull() throws JsonProcessingException {
    pkg.setDescription(null);
    String json = pkg.serializeAsString();
    assertThat(json).contains("\"description\" : null");
  }

  @Test
  void shouldSerializeCustomProperties() throws JsonProcessingException {
    pkg.setCustomProperties(Map.of("acronym", "UFO-X", "numberOfContributors", 100));

    JsonNode node = pkg.serialize();
    JsonNode customProperties = node.get("customProperties");

    //    assertThat(json).contains("\"customProperties\" : {");
    //    assertThat(json).contains("\"acronym\" : \"UFO-X\"");
    //    assertThat(json).contains("\"numberOfContributors\" : 100");
  }

  @Test
  void shouldSerializeEmptyCustomPropertiesAsNull() throws JsonProcessingException {
    pkg.setCustomProperties(null);
    String json = mapper.writeValueAsString(pkg);
    assertThat(json).contains("\"customProperties\" : null");
  }

  @Test
  void shouldSerializeContents() throws JsonProcessingException {
    pkg.createClass("c1", "Person", (String) null);
    String json = mapper.writeValueAsString(pkg);
    assertThat(json).contains("\"contents\" : [ \"c1\" ]");
  }

  @Test
  void shouldSerializeEmptyContentsAsNull() throws JsonProcessingException {
    pkg.setContents();
    String json = mapper.writeValueAsString(pkg);
    assertThat(json).contains("\"contents\" : [ ]");
  }
}

package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.*;
import org.ontouml.utils.BuilderUtils;

public class AnchorSerializerTest {

  Project project;
  Anchor anchor;

  @BeforeEach
  void setUp() throws JsonProcessingException, URISyntaxException {
    project = BuilderUtils.createProject();

    Optional<Anchor> anchor1 = project.getElementById("anchor_1", Anchor.class);
    anchor1.ifPresent(a -> anchor = a);
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    JsonNode node = anchor.serialize();

    String id = node.get("id").asText();
    assertThat(id).isEqualTo("anchor_1");
  }

  @Test
  void shouldSerializeElement() throws JsonProcessingException {
    JsonNode node = anchor.serialize();

    String elementId = node.get("element").asText();
    assertThat(elementId).isEqualTo("class_1");
  }

  @Test
  void shouldSerializeNote() throws JsonProcessingException {
    JsonNode node = anchor.serialize();

    String elementId = node.get("note").asText();
    assertThat(elementId).isEqualTo("note_1");
  }
}

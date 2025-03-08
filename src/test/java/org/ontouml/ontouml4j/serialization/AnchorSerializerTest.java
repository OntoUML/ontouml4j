package org.ontouml.ontouml4j.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.*;
import org.ontouml.ontouml4j.model.Class;

public class AnchorSerializerTest {

  Anchor anchor;

  @BeforeEach
  void setUp() throws JsonProcessingException, URISyntaxException {
    Class clazz = new Class("class_1");

    Note note = new Note("note_1", "My Note text");
    anchor = new Anchor("anchor_1", "My Anchor", clazz, note);
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

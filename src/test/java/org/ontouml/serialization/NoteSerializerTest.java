package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Note;

public class NoteSerializerTest {

  Note note;

  @BeforeEach
  void setUp() {
    note = Note.builder().id("note_1").build();
    note.addText("en", "My Note Text");
    note.addText("pt", "Meu texto");
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    JsonNode node = note.serialize();

    String id = node.get("id").asText();
    assertThat(id).isEqualTo("note_1");
  }

  @Test
  void shouldSerializeText() throws JsonProcessingException {
    JsonNode node = note.serialize();

    String TextEn = node.get("text").get("en").asText();
    String TextPt = node.get("text").get("pt").asText();
    assertThat(TextEn).isEqualTo("My Note Text");
    assertThat(TextPt).isEqualTo("Meu texto");
  }
}

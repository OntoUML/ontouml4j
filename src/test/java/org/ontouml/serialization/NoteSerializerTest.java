package org.ontouml.serialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URISyntaxException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Note;
import org.ontouml.model.Project;
import org.ontouml.utils.BuilderUtils;

public class NoteSerializerTest {

  Project project;
  Note note;

  @BeforeEach
  void setUp() throws JsonProcessingException, URISyntaxException {
    project = BuilderUtils.createProject();

    Optional<Note> note1 = project.getElementById("note_1", Note.class);
    note1.ifPresent(a -> note = a);
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

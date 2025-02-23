package org.ontouml.ontouml4j.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Note;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.utils.ResourceGetter;

public class NoteDeserializerTest {
  static ObjectMapper mapper;
  static Note note;

  @BeforeAll
  static void setUp() throws IOException {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    File jsonFile = ResourceGetter.getJsonFromDeserialization("note.allfields.ontouml.json");
    Project project = mapper.readValue(jsonFile, Project.class);
    Optional<Note> note1 = project.getElementById("note_1", Note.class);
    note1.ifPresent(aNote -> note = aNote);
  }

  @Test
  void shouldDeserializeId() {
    assertThat(note.getId()).isEqualTo("note_1");
  }

  @Test
  void shouldDeserializeName() {
    assertThat(note.getFirstName().isPresent() ? note.getFirstName().get() : null)
        .isEqualTo("My Note");
  }
}

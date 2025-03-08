package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Note;
import org.ontouml.ontouml4j.model.view.NoteView;
import org.ontouml.ontouml4j.shape.Point;
import org.ontouml.ontouml4j.shape.Text;

public class NoteViewSerializerTest {
  static NoteView noteView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Note note = new Note("note_1", "My note text");

    noteView = new NoteView(
        "noteview_1",
        note,
        new Text("text_1", new Point(10, 20), 25, 50));

    node = noteView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("noteview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("note_1");
  }

  @Test
  void shouldSerializeText() throws JsonProcessingException {
    String text = node.get("text").textValue();
    assertThat(text).isEqualTo("text_1");
  }
}

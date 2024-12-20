package org.ontouml.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Note;
import org.ontouml.model.view.NoteView;
import org.ontouml.shape.Point;
import org.ontouml.shape.Text;

public class NoteViewSerializerTest {
  static NoteView noteView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Note note = Note.builder().id("note_1").text(new MultilingualText("My note text")).build();

    noteView =
        NoteView.builder()
            .id("noteview_1")
            .isViewOf(note)
            .text(
                Text.builder().id("text_1").topLeft(new Point(10, 20)).width(25).height(50).build())
            .build();

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

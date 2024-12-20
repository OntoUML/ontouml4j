package org.ontouml.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Anchor;
import org.ontouml.model.Class;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Note;
import org.ontouml.model.view.AnchorView;
import org.ontouml.model.view.ClassView;
import org.ontouml.model.view.NoteView;
import org.ontouml.shape.Path;
import org.ontouml.shape.Point;
import org.ontouml.shape.Text;

public class AnchorViewSerializerTest {

  AnchorView anchorView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Note note = Note.builder().id("note_1").text(new MultilingualText("My note text")).build();
    Class clazz = Class.builder().id("class_1").build();

    Anchor anchor = Anchor.builder().id("anchor_1").note(note).element(clazz).build();

    ClassView classView = ClassView.builder().id("classview_1").isViewOf(clazz).build();
    NoteView noteView =
        NoteView.builder()
            .id("noteview_1")
            .isViewOf(note)
            .text(
                Text.builder().id("text_1").topLeft(new Point(10, 20)).width(25).height(50).build())
            .build();

    Path path = Path.builder().id("path_1").build();

    anchorView =
        AnchorView.builder()
            .id("anchorview_1")
            .isViewOf(anchor)
            .sourceView(noteView)
            .targetView(classView)
            .path(path)
            .build();

    node = anchorView.serialize();
  }

  @Test
  void shouldSerializeId() throws JsonProcessingException {
    String id = node.get("id").asText();

    assertThat(id).isEqualTo("anchorview_1");
  }

  @Test
  void shouldSerializeIsViewOf() throws JsonProcessingException {
    String id = node.get("isViewOf").asText();

    assertThat(id).isEqualTo("anchor_1");
  }

  @Test
  void shouldSerializeNote() {
    String noteId = node.get("sourceView").textValue();

    assertThat(noteId).isEqualTo("noteview_1");
  }

  @Test
  void shouldSerializeModelElement() {
    String classId = node.get("targetView").textValue();

    assertThat(classId).isEqualTo("classview_1");
  }
}

package org.ontouml.ontouml4j.serialization.view;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.ontouml4j.model.Anchor;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.Note;
import org.ontouml.ontouml4j.model.view.AnchorView;
import org.ontouml.ontouml4j.model.view.ClassView;
import org.ontouml.ontouml4j.model.view.NoteView;
import org.ontouml.ontouml4j.shape.Path;
import org.ontouml.ontouml4j.shape.Point;
import org.ontouml.ontouml4j.shape.Text;

public class AnchorViewSerializerTest {

  AnchorView anchorView;
  JsonNode node;

  @BeforeEach
  void beforeEach() throws JsonProcessingException {
    Note note = new Note("note_1", "My Note text");
    Class clazz = new Class("class_1");

    Anchor anchor = new Anchor("anchor_1", "My Anchor", clazz, note);

    ClassView classView = new ClassView("classview_1", clazz);
    NoteView noteView = new NoteView("noteview_1", note, new Text("text_1", new Point(10, 20), 25, 50));

    Path path = new Path("path_1", new ArrayList<>());

    anchorView = new AnchorView("anchorview_1", noteView, classView, path, anchor);

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

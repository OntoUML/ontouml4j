package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Project;
import org.ontouml.model.view.AnchorView;
import org.ontouml.model.view.NoteView;
import org.ontouml.utils.ResourceGetter;

public class AnchorViewDeserializerTest {

  ObjectMapper mapper;
  Project project;
  AnchorView anchorView;

  @BeforeEach
  void setUp() throws IOException {
    mapper = new ObjectMapper();
    File jsonFile = ResourceGetter.getJsonFromDeserialization("anchorView.allfields.ontouml.json");

    try {
      project = mapper.readValue(jsonFile, Project.class);
      Optional<AnchorView> optionalAnchorView =
          project.getElementById("anchorview_1", AnchorView.class);

      optionalAnchorView.ifPresent(value -> anchorView = value);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldDeserializeId() {
    assertThat(anchorView.getId()).isEqualTo("anchorview_1");
  }

  @Test
  void shouldDeserializeType() {
    assertThat(anchorView.getType()).isEqualTo("AnchorView");
  }

  @Test
  void shouldDeserializeSourceView() {
    assertThat(anchorView.getSourceView().getId()).isEqualTo("noteview_1");
    NoteView noteView = (NoteView) anchorView.getSourceView();
    assertThat(noteView.getText().getId()).isEqualTo("text_1");
  }

  @Test
  void shouldDeserializeTargetView() {
    assertThat(anchorView.getTargetView().getId()).isEqualTo("classview_1");
  }

  @Test
  void shouldDeserializePath() {
    assertThat(anchorView.getPath().getId()).isEqualTo("path_1");
    assertThat(anchorView.getPath().getPoints().size()).isEqualTo(2);
  }
}

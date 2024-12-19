package org.ontouml.deserialization;

import static com.google.common.truth.Truth.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ontouml.model.Project;
import org.ontouml.model.view.NoteView;
import org.ontouml.utils.ResourceGetter;

public class NoteViewDeserializerTest {

  ObjectMapper mapper = new ObjectMapper();
  NoteView view;

  @BeforeEach
  void beforeEach() throws IOException {
    File jsonFile = ResourceGetter.getJsonFromDeserialization("noteview.allfields.ontouml.json");
    Project project = mapper.readValue(jsonFile, Project.class);
    Optional<NoteView> noteView = project.getElementById("noteview_1", NoteView.class);
    noteView.ifPresent(aView -> view = aView);
  }

  @Test
  void shouldDeserializeId() {
    assertThat(view.getId()).isEqualTo("noteview_1");
  }

  @Test
  void shouldDeserializeModelElement() {
    assertThat(view.getIsViewOf().getId()).isEqualTo("note_1");
  }

  @Test
  void shouldDeserializeText() {
    assertThat(view.getText().getId()).isEqualTo("text_1");
    assertThat(view.getText().getTopLeft().getX()).isEqualTo(813);
    assertThat(view.getText().getTopLeft().getY()).isEqualTo(20);
  }
}

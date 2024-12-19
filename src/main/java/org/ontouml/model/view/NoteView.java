package org.ontouml.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.view.NoteViewDeserializer;
import org.ontouml.model.Project;
import org.ontouml.serialization.view.NoteViewSerializer;
import org.ontouml.shape.Text;

/** A view element that represents the single occurrence of a note in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = NoteViewDeserializer.class)
@JsonSerialize(using = NoteViewSerializer.class)
public class NoteView extends View {

  /** Identifies the text shape that renders the note view in the diagram. */
  private Text text;

  @Override
  public String getType() {
    return "NoteView";
  }

  @Override
  public void resolveAllReferences(Project project) {
    super.resolveAllReferences(project);

    if (text == null) {
      return;
    }

    Text text = project.getElementById(getText().getId(), Text.class).orElse(null);
    this.setText(text);
  }
}

package org.ontouml.ontouml4j.model.view;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.deserialization.view.NoteViewDeserializer;
import org.ontouml.ontouml4j.model.ModelElement;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.serialization.view.NoteViewSerializer;
import org.ontouml.ontouml4j.shape.Text;

/** A view element that represents the single occurrence of a note in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = NoteViewDeserializer.class)
@JsonSerialize(using = NoteViewSerializer.class)
public class NoteView extends View {

  /** Identifies the text shape that renders the note view in the diagram. */
  private Text text;

  public NoteView(String id, ModelElement isViewOf, Text text) {
    super(id, isViewOf);
    this.text = text;
  }

  public NoteView(String id, ModelElement isViewOf) {
    super(id, isViewOf);
  }

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

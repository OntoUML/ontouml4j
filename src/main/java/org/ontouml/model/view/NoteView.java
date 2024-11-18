package org.ontouml.model.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.shape.Text;

/** A view element that represents the single occurrence of a note in a diagram. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class NoteView extends View {

  /** Identifies the text shape that renders the note view in the diagram. */
  private Text text;

  @Override
  public String getType() {
    return "NoteView";
  }
}

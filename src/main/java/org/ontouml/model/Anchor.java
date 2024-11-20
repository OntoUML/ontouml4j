package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.AnchorDeserializer;

/** A model element that connects a note to a model element it concerns. */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = AnchorDeserializer.class)
public class Anchor extends PackageableElement {
  /** Identifies the note the anchor connects. */
  private Note note;

  private String noteId;

  /** Identifies the model element the anchor connects. */
  private ModelElement element;

  private String elementId;

  @Override
  public String getType() {
    return "Anchor";
  }

  public void buildAllReferences(Project project) {
    Optional<Note> note = project.getElementById(this.noteId, Note.class);
    note.ifPresent(this::setNote);

    Optional<ModelElement> element = project.getElementById(this.elementId, ModelElement.class);
    element.ifPresent(this::setElement);
  }
}

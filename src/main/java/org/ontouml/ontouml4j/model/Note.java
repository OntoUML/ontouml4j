package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.deserialization.NoteDeserializer;
import org.ontouml.ontouml4j.serialization.NoteSerializer;

/**
 * A model element that contains an annotation about the ontology or some of its elements. A note
 * can also be used to represent a constraint in both natural or structured language (i.e.,
 * first-order logic, or OCL).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = NoteDeserializer.class)
@JsonSerialize(using = NoteSerializer.class)
public class Note extends PackageableElement {

  /** Determines the contents of a note using a language string. */
  private MultilingualText text;

  public void addText(String languageTag, String text) {
    if (this.text == null) {
      this.text = new MultilingualText();
    }
    this.text.putText(languageTag, text);
  }

  public void addText(String text) {
    if (this.text == null) {
      this.text = new MultilingualText();
    }
    this.text.putText("en", text);
  }

  @Override
  public String getType() {
    return "Note";
  }
}

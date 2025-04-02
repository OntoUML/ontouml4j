package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.ontouml.ontouml4j.deserialization.NoteDeserializer;
import org.ontouml.ontouml4j.serialization.NoteSerializer;

/**
 * A model element that contains an annotation about the ontology or some of its
 * elements. A note
 * can also be used to represent a constraint in both natural or structured
 * language (i.e.,
 * first-order logic, or OCL).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@JsonDeserialize(using = NoteDeserializer.class)
@JsonSerialize(using = NoteSerializer.class)
public class Note extends PackageableElement {

  /** Determines the contents of a note using a language string. */
  private MultilingualText text = new MultilingualText();

  public Note(String id, String text) {
    super(id);
    addText(text);
  }

  public Note(String id, MultilingualText text) {
    super(id);
    this.text = text;
  }

  public Note(String id) {
    super(id);
  }

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

  public String getFirstText() {
    return this.text.getMap().values().stream().toList().getFirst();
  }
}

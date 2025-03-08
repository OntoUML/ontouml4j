package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Optional;
import lombok.*;
import org.ontouml.ontouml4j.serialization.NamedElementSerializer;

/**
 * An identified element of an OntoUML ontology according to the OntoUML Metamodel, which includes
 * projects, model elements, diagrams, views, and shapes.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@JsonSerialize(using = NamedElementSerializer.class)
public abstract class NamedElement extends OntoumlElement {

  /** Identifies the agents who contributed to the creation of the named element. */
  List<Resource> creators;

  /** Identifies the agents who contributed to the development of the named element. */
  List<Resource> contributors;

  /** Determines the name of the named element using language string. */
  private MultilingualText name;

  /**
   * Determines alternative names of the named element using an array of language strings.
   * Alternative names are not translations of the named element, but indeed alternatives or
   * synonyms to the main one.
   */
  private List<MultilingualText> alternativeNames;

  /** Determines a free-text account of the named element using language string. */
  private MultilingualText description;

  /**
   * Determines general notes about the named element using an array of language strings. Editorial
   * notes are typically notes on the development process and must not be confused with
   * descriptions.
   */
  private List<MultilingualText> editorialNotes;

  public NamedElement(String id) {
    super(id);
  }

  public NamedElement(String id, MultilingualText name) {
    super(id);
    this.name = name;
  }

  public NamedElement(String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id);
    this.name = name;
    this.alternativeNames = alternativeNames;
  }

  public NamedElement() {
    super();
  }

  public Optional<String> getNameIn(String language) {
    return name.getText(language);
  }

  public Optional<String> getFirstName() {
    if (name == null) {
      return Optional.empty();
    }
    return name.getText();
  }

  public void addName(String languageTag, String value) {
    if (value == null) {
      return;
    }
    if (this.name == null) {
      this.name = new MultilingualText(languageTag, value);
      return;
    }
    this.name.putText(languageTag, value);
  }

  public void addName(String value) {
    if (value == null) {
      return;
    }
    if (this.name == null) {
      this.name = new MultilingualText(value);
    }
    this.name.putText(value);
  }

  public void removeNameIn(String languageTag) {
    this.name.removeTextIn(languageTag);
  }

  public void removeAllNames() {
    this.name.removeAll();
  }

  public Optional<String> getDescriptionIn(String language) {
    return description.getText(language);
  }

  public Optional<String> getFirstDescription() {
    return description.getText();
  }

  public void addDescription(String languageTag, String value) {
    if (this.description == null) {
      this.description = new MultilingualText(languageTag, value);
    }
    this.description.putText(languageTag, value);
  }

  public void addDescription(String value) {
    if (this.description == null) {
      this.description = new MultilingualText(value);
    }
    this.description.putText(value);
  }

  public void removeDescription(String languageTag) {
    this.description.removeTextIn(languageTag);
  }

  public void removeAllDescriptions() {
    this.description.removeAll();
  }

  @Override
  public String toString() {
    return getType() + " { id: " + id + "(hash: " + hashCode() + "), name: " + getName() + "}";
  }
}

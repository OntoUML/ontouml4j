package org.ontouml.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * An identified element of an OntoUML ontology according to the OntoUML Metamodel, which includes projects, model
 * elements, diagrams, views, and shapes.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class NamedElement extends OntoumlElement {
  /**
   * Determines the name of the named element using language string.
   */
  private MultilingualText name;
  /**
   * Determines alternative names of the named element using an array of language strings. Alternative names are not
   * translations of the named element, but indeed alternatives or synonyms to the main one.
   */
  private List<MultilingualText> alternativeNames;
  /**
   * Determines a free-text account of the named element using language string.
   */
  private MultilingualText description;
  /**
   * Determines general notes about the named element using an array of language strings. Editorial notes are typically
   * notes on the development process and must not be confused with descriptions.
   */
  private List<MultilingualText> editorialNotes;
  /**
   * Identifies the agents who contributed to the creation of the named element.
   */
  List<Resource> creators;
  /**
   * Identifies the agents who contributed to the development of the named element.
   */
  List<Resource> contributors;

  public NamedElement(String id, MultilingualText name) {
    super(id);
    this.name = name;
  }

  public NamedElement(String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id);
    this.name = name;
    this.alternativeNames = alternativeNames;
  }

  @Override
  public String toString() {
    return getType() + " { id: " + id + "(hash: " + hashCode() + "), name: " + getName() + "}";
  }
}

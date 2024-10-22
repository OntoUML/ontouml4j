package org.ontouml.view;


import org.ontouml.MultilingualText;
import org.ontouml.OntoumlElement;

/** Element defined in the concrete syntax of the language */
public abstract class DiagramElement extends OntoumlElement {

  public DiagramElement(String id, MultilingualText name) {
    super(id, name, null, null);
  }

  public DiagramElement(String id) {
    super(id, null, null, null);
  }
}

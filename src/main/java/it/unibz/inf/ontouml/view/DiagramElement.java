package it.unibz.inf.ontouml.view;


import it.unibz.inf.ontouml.MultilingualText;
import it.unibz.inf.ontouml.OntoumlElement;

/** Element defined in the concrete syntax of the language */
public abstract class DiagramElement extends OntoumlElement {

  public DiagramElement(String id, MultilingualText name) {
    super(id, name);
  }

  public DiagramElement(String id) {
    super(id, null);
  }
}

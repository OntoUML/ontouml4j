package org.ontouml.ontouml4j.model.placeholders;

import org.ontouml.ontouml4j.model.ModelElement;

public class UnresolvedModelElement extends ModelElement {

  public UnresolvedModelElement(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "UnresolvedModelElement";
  }
}

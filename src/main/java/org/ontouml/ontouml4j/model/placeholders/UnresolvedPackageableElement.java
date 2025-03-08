package org.ontouml.ontouml4j.model.placeholders;

import org.ontouml.ontouml4j.model.PackageableElement;

public class UnresolvedPackageableElement extends PackageableElement {

  public UnresolvedPackageableElement(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "UnresolvedPackageableElement";
  }
}

package org.ontouml.ontouml4j.model.placeholders;

import org.ontouml.ontouml4j.model.view.View;

public class UnresolvedView extends View {

  public UnresolvedView(String id) {
    super(id);
  }

  @Override
  public String getType() {
    return "UnresolvedView";
  }
}

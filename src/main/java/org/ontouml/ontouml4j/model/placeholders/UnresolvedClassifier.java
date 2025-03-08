package org.ontouml.ontouml4j.model.placeholders;

import org.ontouml.ontouml4j.model.Classifier;
import org.ontouml.ontouml4j.model.stereotype.Stereotype;

/**
 * This is a placeholder for a Classifier, in order to be used internally by the library when a
 * Classifier is referenced but not yet resolved.
 *
 * @param <T>
 * @param <S>
 */
public class UnresolvedClassifier<T extends Classifier<T, S>, S extends Stereotype>
    extends Classifier<T, S> {

  public UnresolvedClassifier(String id) {
    super(id);
  }

  @Override
  public void setStereotype(String stereotypeName) {}

  @Override
  public String getType() {
    return "UnresolvedClassifier";
  }
}

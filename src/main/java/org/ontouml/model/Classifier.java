package org.ontouml.model;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.Stereotype;

/**
 * A decoratable element (either a class or a relation) that defines properties exhibited by its
 * instances.
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public abstract class Classifier<T extends Classifier<T, S>, S extends Stereotype>
    extends PackageableElement {

  /**
   * Determines whether the classifier can have direct instances using a boolean. Abstract
   * classifiers can only have instances when these are instances of some other classifier that is
   * not abstract (i.e., concrete) and is a specialization of the abstract one.
   */
  private boolean isAbstract;

  /**
   * Identifies the properties contained in a classifier. These properties are referred to as
   * attributes when contained by classes, and relation ends when contained by relations. In the
   * case of relations, the properties array must be ordered.
   */
  private List<String> properties;
}

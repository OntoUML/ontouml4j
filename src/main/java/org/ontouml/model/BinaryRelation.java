package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.RelationStereotype;

import java.util.Optional;

/**
 * A relation that defines the properties of a set of binary relations of the subject domain.
 * Examples include "studies in", and derivation relations (e.g., between material relations and
 * relators). A binary relation may either connect two classes, or a relation (as source) and a
 * class (as target) in the case of derivation relations connecting descriptive relations to the
 * classes that serve as their truthmakers (as in the relation between the material relation
 * "studies in" and the "Enrollment" relator).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class BinaryRelation extends Relation {
  @Override
  public String getType() {
    return "BinaryRelation";
  }

  @Override
  public void setStereotype(String stereotypeName) {
    Optional<RelationStereotype> stereotype = RelationStereotype.findByName(stereotypeName);

    stereotype.ifPresentOrElse(
        s -> setOntoumlStereotype(stereotype.get()), () -> setCustomStereotype(stereotypeName));
  }
}

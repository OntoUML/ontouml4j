package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.RelationStereotype;

import java.util.Optional;

/**
 * A relation that defines the properties of a set of relations of the subject domain that connect
 * more than two members. Examples include "studies in", "buys product from" (ternary relation), and
 * derivation relations (e.g., between material relations and relators).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class NaryRelation extends Relation {
  @Override
  public String getType() {
    return "NaryRelation";
  }

  @Override
  public void setStereotype(String stereotypeName) {
    Optional<RelationStereotype> stereotype = RelationStereotype.findByName(stereotypeName);

    stereotype.ifPresentOrElse(
        s -> setOntoumlStereotype(stereotype.get()), () -> setCustomStereotype(stereotypeName));
  }
}

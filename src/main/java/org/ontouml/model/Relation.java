package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.RelationStereotype;

/**
 * A classifier that defines the properties of a set of relations of the subject domain. Examples
 * include "studies in", "buys product from" (ternary relation), and derivation relations (e.g.,
 * between material relations and relators).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Relation extends Classifier<Relation, RelationStereotype> {
  public Relation(String id, MultilingualText name, RelationStereotype ontoumlStereotype) {
    super(id, name, ontoumlStereotype);
  }

  public Relation(String id, MultilingualText name, String ontoumlStereotype) {
    super(id, name, ontoumlStereotype);
  }
}

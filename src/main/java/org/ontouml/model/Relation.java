package org.ontouml.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
public abstract class Relation extends Classifier<Relation, RelationStereotype> {}

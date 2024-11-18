package org.ontouml.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * A relation that defines the properties of a set of relations of the subject domain that connect more
 * than two members.
 * Examples include "studies in", "buys product from" (ternary relation), and derivation relations
 * (e.g., between material relations and relators).
 */
@Data
@SuperBuilder
public class NaryRelation {}

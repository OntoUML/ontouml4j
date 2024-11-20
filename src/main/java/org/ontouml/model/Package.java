package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.PackageDeserializer;

/**
 * A model element that can group other model elements that are referred to as "packageable
 * elements." Package elements are used to perform the modularization of an ontology. While the
 * OntoUML Metamodel does not require package elements to follow a tree structure (i.e., it allows
 * overlapping packages), ontologies that require UML representations should adhere to this
 * constraint for compatibility.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = PackageDeserializer.class)
public class Package extends ModelElement {

  /** List the ids of the contents of a package */
  List<String> contentIds = new ArrayList<>();

  /** Identifies the contents of a package element. */
  List<PackageableElement> contents = new ArrayList<>();

  @Override
  public String getType() {
    return "Package";
  }
}

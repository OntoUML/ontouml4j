package org.ontouml.ontouml4j.model;

import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
// @JsonSerialize(using = PackageableElementSerializer.class)
public abstract class PackageableElement extends ModelElement {
  public PackageableElement(
      String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id, name, alternativeNames);
  }

  public PackageableElement() {
    super();
  }
}

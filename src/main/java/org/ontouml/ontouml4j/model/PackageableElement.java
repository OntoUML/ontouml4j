package org.ontouml.ontouml4j.model;

import java.util.List;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
// @JsonSerialize(using = PackageableElementSerializer.class)
public abstract class PackageableElement extends ModelElement {
  public PackageableElement(String id) {
    super(id);
  }

  public PackageableElement(
      String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id, name, alternativeNames);
  }

  public PackageableElement(String id, MultilingualText name) {
    super(id, name);
  }

  public PackageableElement() {
    super();
  }
}

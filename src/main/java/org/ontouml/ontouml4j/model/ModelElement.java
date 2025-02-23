package org.ontouml.ontouml4j.model;

import java.util.List;
import java.util.Map;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * A named element that represents an element of the language's abstract syntax (e.g., a class, a
 * relation, or a generalization).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
public abstract class ModelElement extends NamedElement {
  /**
   * Determines custom properties of the model element using key-value pairs. In UML, for instance,
   * this custom properties are represented through tagged values.
   */
  Map<String, Object> customProperties;

  public ModelElement(String id, MultilingualText name) {
    super(id, name);
  }

  public ModelElement(String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    super(id, name, alternativeNames);
  }

  public ModelElement() {
    super();
  }

  public void addCustomProperty(String key, Object value) {
    customProperties.put(key, value);
  }
}

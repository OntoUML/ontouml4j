package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.LiteralDeserializer;

/**
 * A model element that represents a specific value within an enumerated set of values. Examples
 * include each letter in an A to F letter grading scale, listed in a class "Letter Grade" decorated
 * with the stereotype "enumeration".
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
@JsonDeserialize(using = LiteralDeserializer.class)
public class Literal extends ModelElement {

  public Literal(String id, MultilingualText name) {
    super(id, name, new ArrayList<>());
  }

  public Literal(String name) {
    this(null, new MultilingualText(name));
  }

  @Override
  public String getType() {
    return "Literal";
  }
}

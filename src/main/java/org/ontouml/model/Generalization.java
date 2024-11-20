package org.ontouml.model;

import java.util.ArrayList;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.ontouml.model.stereotype.Stereotype;

/**
 * A model element that represents the generalization of a specific classifier into a general
 * classifier. When read in the inverse direction, a generalization is referred to as a
 * specialization. Examples include the generalization of a specific class "Student" into a general
 * class "Person," and the generalization of a specific relation "close friends with" into a general
 * relation "friends with". A generalization can only connect two classifiers of the same type,
 * i.e., it can either connect two class elements or two relation elements.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class Generalization extends ModelElement {
  private Classifier<?, ?> general;
  private Classifier<?, ?> specific;

  public <T extends Classifier<T, S>, S extends Stereotype> Generalization(
      String id, MultilingualText name, Classifier<T, S> specific, Classifier<T, S> general) {
    super(id, name, new ArrayList<>());
    setGeneral(general);
    setSpecific(specific);
  }

  public <T extends Classifier<T, S>, S extends Stereotype> Generalization(
      String id, String name, Classifier<T, S> specific, Classifier<T, S> general) {
    this(id, new MultilingualText(name), specific, general);
  }

  public <T extends Classifier<T, S>, S extends Stereotype> Generalization(
      String id, Classifier<T, S> specific, Classifier<T, S> general) {
    this(id, (MultilingualText) null, specific, general);
  }

  public <T extends Classifier<T, S>, S extends Stereotype> Generalization(
      Classifier<T, S> specific, Classifier<T, S> general) {
    this(null, (MultilingualText) null, specific, general);
  }
  
  public Optional<Classifier<?, ?>> getGeneral() {
    return Optional.ofNullable(general);
  }

  public Optional<Classifier<?, ?>> getSpecific() {
    return Optional.ofNullable(specific);
  }

  @Override
  public String getType() {
    return "Generalization";
  }
}

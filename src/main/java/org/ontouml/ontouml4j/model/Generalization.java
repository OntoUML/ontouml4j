package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.ontouml.ontouml4j.deserialization.GeneralizationDeserializer;
import org.ontouml.ontouml4j.model.stereotype.Stereotype;
import org.ontouml.ontouml4j.serialization.GeneralizationSerializer;

/**
 * A model element that represents the generalization of a specific classifier
 * into a general
 * classifier. When read in the inverse direction, a generalization is referred
 * to as a
 * specialization. Examples include the generalization of a specific class
 * "Student" into a general
 * class "Person," and the generalization of a specific relation "close friends
 * with" into a general
 * relation "friends with". A generalization can only connect two classifiers of
 * the same type,
 * i.e., it can either connect two class elements or two relation elements.
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@JsonDeserialize(using = GeneralizationDeserializer.class)
@JsonSerialize(using = GeneralizationSerializer.class)
public class Generalization extends PackageableElement {
  private String generalId;
  private String specificId;

  /**
   * Identifies the general classifier in a generalization element. E.g., in the
   * generalization of
   * "Student" into "Person", "Person" is the general classifier.
   */
  private Classifier<?, ?> general;

  /**
   * Identifies the general classifier in a generalization element. E.g., in the
   * generalization of
   * "Student" into "Person", "Student" is the specific classifier.
   */
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

  public void buildAllReferences(Project project) {
    Optional<Classifier> general = project.getElementById(generalId, Classifier.class);

    general.ifPresent(
        gen -> {
          if (gen instanceof Classifier) {
            setGeneral((Classifier<?, ?>) gen);
          }
        });

    Optional<ModelElement> specific = project.getElementById(specificId, ModelElement.class);

    specific.ifPresent(
        spec -> {
          if (spec instanceof Classifier) {
            setSpecific((Classifier<?, ?>) spec);
          }
        });
  }

  public String getGeneralId() {
    return generalId;
  }

  public void setGeneralId(String generalId) {
    this.generalId = generalId;
  }

  public String getSpecificId() {
    return specificId;
  }

  public void setSpecificId(String specificId) {
    this.specificId = specificId;
  }

  public void setGeneral(Classifier<?, ?> general) {
    this.general = general;
  }

  public void setSpecific(Classifier<?, ?> specific) {
    this.specific = specific;
  }
}

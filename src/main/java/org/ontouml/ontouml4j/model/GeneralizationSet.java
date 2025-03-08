package org.ontouml.ontouml4j.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ontouml.ontouml4j.OntoumlUtils;
import org.ontouml.ontouml4j.deserialization.GeneralizationSetDeserializer;
import org.ontouml.ontouml4j.serialization.GeneralizationSetSerializer;

/**
 * A model element that contains an annotation about the ontology or some of its elements. A note
 * can also be used to represent a constraint in both natural or structured language (i.e.,
 * first-order logic, or OCL).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonDeserialize(using = GeneralizationSetDeserializer.class)
@JsonSerialize(using = GeneralizationSetSerializer.class)
public class GeneralizationSet extends PackageableElement {
  /**
   * Determines whether the specific classifiers in the generalization set have disjoint extensions.
   * Examples include the generalization set involving "Child" and "Adult" generalized into
   * "Person", where the "is disjoint" as "true" indicates that no person is simultaneously an
   * instance of "Child" and "Adult."
   */
  private boolean isDisjoint;

  /**
   * Determines whether the specific classifiers in the generalization set completely cover the
   * extension of the general classifier. Examples include the generalization set involving "Child"
   * and "Adult" generalized into "Person", where the "is complete" as "true" indicates that every
   * person is either an instance of "Child" or "Adult."
   */
  private boolean isComplete;

  /** Identifies all generalizations that are involved by the generalization set. */
  private Set<Generalization> generalizations = new HashSet<>();

  private List<String> generalizationIds = new ArrayList<>();

  /**
   * Identifies the high-order class that classifies (i.e., is instantiated by) every specific class
   * in the generalization set. For example, "Academic Role" as the categorizer of the
   * generalization set of "Person" into "Student" and "Teacher" representing the specific classes
   * as instances of the categorizer. A categorizer can only be present in generalization sets
   * involving exclusively classes.
   */
  private Class categorizer;

  private String categorizerId;

  public GeneralizationSet(
      String id,
      MultilingualText name,
      Class categorizer,
      Collection<Generalization> generalizations) {
    super(id, name, new ArrayList<>());
    this.generalizations = new HashSet<>(generalizations);
    OntoumlUtils.addIfNotNull(this.generalizations, generalizations);
    this.categorizer = categorizer;
  }

  public GeneralizationSet(
      String id, String name, Class categorizer, Collection<Generalization> generalizations) {
    this(id, new MultilingualText(name), categorizer, generalizations);
  }

  public GeneralizationSet(String id, String name, Collection<Generalization> generalizations) {
    this(id, new MultilingualText(name), null, generalizations);
  }

  public GeneralizationSet(String id, String name, Generalization... generalizations) {
    this(id, new MultilingualText(name), null, Arrays.asList(generalizations));
  }

  public GeneralizationSet(
      MultilingualText name, Class categorizer, Collection<Generalization> generalizations) {
    this(null, name, categorizer, generalizations);
  }

  public GeneralizationSet(Class categorizer, Collection<Generalization> generalizations) {
    this(null, (MultilingualText) null, categorizer, generalizations);
  }

  public GeneralizationSet(String name, Collection<Generalization> generalizations) {
    this(null, new MultilingualText(name), null, generalizations);
  }

  public GeneralizationSet(Collection<Generalization> generalizations) {
    this(null, (MultilingualText) null, null, generalizations);
  }

  public GeneralizationSet(String name, Generalization... generalizations) {
    this(null, new MultilingualText(name), null, Arrays.asList(generalizations));
  }

  public boolean isDisjoint() {
    return isDisjoint;
  }

  public void setDisjoint(boolean disjoint) {
    isDisjoint = disjoint;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean complete) {
    isComplete = complete;
  }

  public Optional<Class> getCategorizer() {
    return Optional.ofNullable(categorizer);
  }

  public Set<Generalization> getGeneralizations() {
    return new HashSet<>(generalizations);
  }

  public void setGeneralizations(Collection<Generalization> generalizations) {
    this.generalizations.clear();
    OntoumlUtils.addIfNotNull(this.generalizations, generalizations);
  }

  public void addGeneralization(Generalization generalization) {
    if (generalization == null)
      throw new NullPointerException("Cannot add a null generalization to the generalization set.");

    this.generalizations.add(generalization);
  }

  @Override
  public String getType() {
    return "GeneralizationSet";
  }

  public void buildAllReferences(Project project) {
    Optional<Class> categorizer = project.getElementById(categorizerId, Class.class);
    categorizer.ifPresent(this::setCategorizer);

    this.generalizationIds.forEach(
        generalizationId -> {
          Optional<Generalization> generalization =
              project.getElementById(generalizationId, Generalization.class);
          generalization.ifPresent(this::addGeneralization);
        });
  }

  @Override
  public String toString() {
    return "GeneralizationSet{"
        + "name="
        + getName()
        + ", generalizations="
        + generalizations
        + '}';
  }
}

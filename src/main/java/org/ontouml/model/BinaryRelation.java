package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.BinaryRelationDeserializer;
import org.ontouml.model.stereotype.ClassStereotype;
import org.ontouml.model.stereotype.RelationStereotype;
import org.ontouml.model.stereotype.Stereotype;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * A relation that defines the properties of a set of binary relations of the subject domain.
 * Examples include "studies in", and derivation relations (e.g., between material relations and
 * relators). A binary relation may either connect two classes, or a relation (as source) and a
 * class (as target) in the case of derivation relations connecting descriptive relations to the
 * classes that serve as their truthmakers (as in the relation between the material relation
 * "studies in" and the "Enrollment" relator).
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonDeserialize(using = BinaryRelationDeserializer.class)
public class BinaryRelation extends Relation {
  @Override
  public String getType() {
    return "BinaryRelation";
  }

  @Override
  public void setStereotype(String stereotypeName) {
    Optional<RelationStereotype> stereotype = RelationStereotype.findByName(stereotypeName);

    stereotype.ifPresentOrElse(
        s -> setOntoumlStereotype(stereotype.get()), () -> setCustomStereotype(stereotypeName));
  }

  public int arity() {
    return properties != null ? properties.size() : 0;
  }

  public List<Property> getRelationEnds() {
    return properties;
  }

  public Property getSourceEnd() {
    if (!isBinary())
      throw new IllegalCallerException("Can only retrieve source end of binary relation.");

    return properties.getFirst();
  }

  public Property getTargetEnd() {
    if (!isBinary())
      throw new IllegalCallerException("Can only retrieve target end of binary relation.");

    return properties.get(1);
  }

  public Property getMemberEnd(int position) {
    if (position < 0 || position >= properties.size())
      throw new IndexOutOfBoundsException("Cannot retrieve end at position " + position + ".");

    return properties.get(position);
  }

  public Property getDerivedRelationEnd() {
    if (!fromRelationToClass())
      throw new IllegalCallerException(
          "Can only retrieve the derived relation end of a derivation relation.");

    return getSourceEnd();
  }

  public Property getDerivingClassEnd() {
    if (!fromRelationToClass())
      throw new IllegalCallerException(
          "Can only retrieve the deriving class end of a derivation relation.");

    return getTargetEnd();
  }

  public Classifier<?, ?> getSource() {
    Optional<Classifier<?, ?>> source = getSourceEnd().getPropertyType();

    if (source.isEmpty()) throw new NullPointerException("Source of the relation is null.");

    return source.get();
  }

  public Classifier<?, ?> getTarget() {
    Optional<Classifier<?, ?>> target = getTargetEnd().getPropertyType();

    if (target.isEmpty()) throw new NullPointerException("Target of the relation is null.");

    return target.get();
  }

  public Classifier<?, ?> getMember(int position) {
    Optional<Classifier<?, ?>> member = getMemberEnd(position).getPropertyType();

    if (member.isEmpty())
      throw new NullPointerException("Member at position " + position + " is null.");

    return member.get();
  }

  public Class getSourceClass() {
    Classifier<?, ?> source = getSource();

    if (!(source instanceof Class))
      throw new IllegalCallerException("Source of the relation is not a class.");

    return (Class) source;
  }

  public Class getTargetClass() {
    Classifier<?, ?> target = getTarget();

    if (!(target instanceof Class))
      throw new IllegalCallerException("Target of the relation is not a class.");

    return (Class) target;
  }

  public Class getMemberClass(int position) {
    Classifier<?, ?> member = getMember(position);

    if (!(member instanceof Class))
      throw new IllegalCallerException("Target of the relation is not a class.");

    return (Class) member;
  }

  public Relation getDerivedRelation() {
    Optional<Classifier<?, ?>> relation = getDerivedRelationEnd().getPropertyType();

    if (relation.isEmpty()) throw new NullPointerException("The derived relation is null.");

    if (!(relation.get() instanceof Relation))
      throw new IllegalCallerException("The derived element is not a relation.");

    return (Relation) relation.get();
  }

  public Class getDerivingClass() {
    Optional<Classifier<?, ?>> _class = getDerivingClassEnd().getPropertyType();

    if (_class.isEmpty())
      throw new NullPointerException("The class from which the relation is derived is null.");

    if (!(_class.get() instanceof Class))
      throw new IllegalCallerException(
          "The element from which the relation is derived is not a class.");

    return (Class) _class.get();
  }

  public Optional<? extends Stereotype> getSourceOntoumlStereotype() {
    return getSource().getOntoumlStereotype();
  }

  public Optional<? extends Stereotype> getTargetOntoumlStereotype() {
    return getTarget().getOntoumlStereotype();
  }

  public Optional<ClassStereotype> getSourceClassStereotype() {
    return getSourceClass().getOntoumlStereotype();
  }

  public Optional<ClassStereotype> getTargetClassStereotype() {
    return getTargetClass().getOntoumlStereotype();
  }

  public Optional<ClassStereotype> getMemberClassStereotype(int position) {
    return getMemberClass(position).getOntoumlStereotype();
  }

  public Optional<RelationStereotype> getDerivedRelationStereotype() {
    return getDerivedRelation().getOntoumlStereotype();
  }

  public Optional<ClassStereotype> getDerivingClassStereotype() {
    return getDerivingClass().getOntoumlStereotype();
  }

  public Optional<String> getSourceStereotype() {
    return getSource().getStereotype();
  }

  public Optional<String> getTargetStereotype() {
    return getTarget().getStereotype();
  }

  public boolean isBinary() {
    return arity() == 2;
  }

  public boolean isTernary() {
    return arity() == 3;
  }

  public boolean holdsBetweenClasses() {
    if (arity() < 2) return false;
    return properties.stream().allMatch(Property::isPropertyTypeClass);
  }

  public boolean holdsBetweenRelations() {
    if (arity() < 2) return false;
    return properties.stream().allMatch(Property::isPropertyTypeRelation);
  }

  public boolean holdsBetweenClassAndRelation() {
    if (arity() < 2) return false;
    return properties.stream().anyMatch(Property::isPropertyTypeRelation)
        && properties.stream().anyMatch(Property::isPropertyTypeClass);
  }

  public boolean holdsBetweenEvents() {
    if (arity() < 2) return false;

    return properties.stream()
        .allMatch(
            p -> p.isPropertyTypeClass() && p.getPropertyTypeAsClass().isRestrictedToEvents());
  }

  public boolean holdsBetweenMoments() {
    if (arity() < 2) return false;

    return properties.stream()
        .allMatch(
            p -> p.isPropertyTypeClass() && p.getPropertyTypeAsClass().isRestrictedToMoments());
  }

  public boolean holdsBetweenSubstantials() {
    if (arity() < 2) return false;

    return properties.stream()
        .allMatch(
            p ->
                p.isPropertyTypeClass() && p.getPropertyTypeAsClass().isRestrictedToSubstantials());
  }

  public boolean isBinaryClassRelation() {
    return isBinary() && holdsBetweenClasses();
  }

  public boolean fromRelationToClass() {
    return isBinary() && getSource() instanceof Relation && getTarget() instanceof Class;
  }

  public boolean isTernaryClassRelation() {
    return isTernary() && holdsBetweenClasses();
  }

  public boolean isPartWholeRelation() {
    return isBinaryClassRelation() && getTargetEnd().isAggregationEnd();
  }

  public boolean isExistentialDependency() {
    return properties.stream().anyMatch(Property::isReadOnly);
  }

  public boolean isSourceExistentiallyDependent() {
    return getTargetEnd().isReadOnly();
  }

  public boolean isTargetExistentiallyDependent() {
    return getSourceEnd().isReadOnly();
  }

  public boolean isBinaryExistentialDependency() {
    return isSourceExistentiallyDependent() || isTargetExistentiallyDependent();
  }

  public boolean isExistentialDependence() {
    Optional<RelationStereotype> stereotype = getOntoumlStereotype();
    return stereotype.isPresent() && stereotype.get().isExistentialDependency();
  }

  public boolean isMaterial() {
    return getOntoumlStereotype().orElse(null) == RelationStereotype.MATERIAL;
  }

  public boolean isDerivation() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.DERIVATION;
  }

  public boolean isComparative() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.COMPARATIVE;
  }

  public boolean isMediation() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.MEDIATION;
  }

  public boolean isCharacterization() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.CHARACTERIZATION;
  }

  public boolean isExternalDependence() {
    return isBinary()
        && getOntoumlStereotype().orElse(null) == RelationStereotype.EXTERNAL_DEPENDENCE;
  }

  public boolean isComponentOf() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.COMPONENT_OF;
  }

  public boolean isMemberOf() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.MEMBER_OF;
  }

  public boolean isSubCollectionOf() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.SUBCOLLECTION_OF;
  }

  public boolean isSubQuantityOf() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.SUBQUANTITY_OF;
  }

  public boolean isInstantiation() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.INSTANTIATION;
  }

  public boolean isTermination() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.TERMINATION;
  }

  public boolean isParticipational() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.PARTICIPATIONAL;
  }

  public boolean isParticipation() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.PARTICIPATION;
  }

  public boolean isHistoricalDependence() {
    return isBinary()
        && getOntoumlStereotype().orElse(null) == RelationStereotype.HISTORICAL_DEPENDENCE;
  }

  public boolean isCreation() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.CREATION;
  }

  public boolean isManifestation() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.MANIFESTATION;
  }

  public boolean isBringsAbout() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.BRINGS_ABOUT;
  }

  public boolean isTriggers() {
    return isBinary() && getOntoumlStereotype().orElse(null) == RelationStereotype.TRIGGERS;
  }

  public boolean propertiesSatisfy(Predicate<Property> condition) {
    return propertiesSatisfy(List.of(condition));
  }

  public boolean propertiesSatisfy(List<Predicate<Property>> conditions) {
    if (conditions == null) throw new NullPointerException("Conditions list cannot be null.");

    if (arity() != conditions.size()) {
      throw new IllegalArgumentException(
          "The number of conditions must be the same as that of properties of the relation.");
    }

    for (int i = 0; i < conditions.size(); i++) {
      if (!conditions.get(i).test(properties.get(i))) return false;
    }

    return true;
  }

  public Class getMediated() {
    return getMediatedEnd().getPropertyTypeAsClass();
  }

  public Property getMediatedEnd() {
    if (!isMediation())
      throw new IllegalCallerException("Can only retrieve mediated end of a mediation!");

    return getTargetEnd();
  }

  public Class getRelator() {
    return getRelatorEnd().getPropertyTypeAsClass();
  }

  public Property getRelatorEnd() {
    if (!isMediation())
      throw new IllegalCallerException("Can only retrieve relator end of a mediation!");

    return getSourceEnd();
  }

  public Class getBearer() {
    return getBearerEnd().getPropertyTypeAsClass();
  }

  public Property getBearerEnd() {
    if (!isCharacterization())
      throw new IllegalCallerException("Can only retrieve bearer end of a characterization!");

    return getTargetEnd();
  }

  public Class getCharacterizer() {
    return getCharacterizerEnd().getPropertyTypeAsClass();
  }

  public Property getCharacterizerEnd() {
    if (!isCharacterization())
      throw new IllegalCallerException(
          "Can only retrieve characterizer (.e.g Mode, Quality) end of a characterization!");

    return getSourceEnd();
  }

  public Class getDependee() {
    return getDependeeEnd().getPropertyTypeAsClass();
  }

  public Property getDependeeEnd() {
    if (!isExternalDependence())
      throw new IllegalCallerException(
          "Can only retrieve dependee end of an external dependence relation!");

    return getTargetEnd();
  }

  public Class getDepender() {
    return getDependerEnd().getPropertyTypeAsClass();
  }

  public Property getDependerEnd() {
    if (!isExternalDependence())
      throw new IllegalCallerException(
          "Can only retrieve depender end of an external dependence relation!");

    return getSourceEnd();
  }

  public Class getHistoricalDependee() {
    return getHistoricalDependeeEnd().getPropertyTypeAsClass();
  }

  public Property getHistoricalDependeeEnd() {
    if (!isHistoricalDependence())
      throw new IllegalCallerException(
          "Can only retrieve historical dependee end of a historical dependence relation!");

    return getTargetEnd();
  }

  public Class getHistoricalDepender() {
    return getHistoricalDependerEnd().getPropertyTypeAsClass();
  }

  public Property getHistoricalDependerEnd() {
    if (!isExternalDependence())
      throw new IllegalCallerException(
          "Can only retrieve historical depender end of an historical dependence relation!");

    return getSourceEnd();
  }

  public Class getParticipant() {
    return getParticipantEnd().getPropertyTypeAsClass();
  }

  public Property getParticipantEnd() {
    if (!isParticipation())
      throw new IllegalCallerException(
          "Can only retrieve participant end of a participation relation!");

    return getTargetEnd();
  }

  public Class getParticipation() {
    return getParticipationEnd().getPropertyTypeAsClass();
  }

  public Property getParticipationEnd() {
    if (!isParticipation())
      throw new IllegalCallerException(
          "Can only retrieve participation (aka event) end of a participation relation!");

    return getSourceEnd();
  }

  public Class getCreatedClass() {
    return getCreatedEnd().getPropertyTypeAsClass();
  }

  public Property getCreatedEnd() {
    if (!isCreation())
      throw new IllegalCallerException("Can only retrieve created end of a creation relation!");

    return getTargetEnd();
  }

  public Class getCreationEvent() {
    return getCreationEventEnd().getPropertyTypeAsClass();
  }

  public Property getCreationEventEnd() {
    if (!isCreation())
      throw new IllegalCallerException(
          "Can only retrieve creation event end of a creation relation!");

    return getSourceEnd();
  }

  public Class getTerminated() {
    return getTerminatedEnd().getPropertyTypeAsClass();
  }

  public Property getTerminatedEnd() {
    if (!isTermination())
      throw new IllegalCallerException("Can only retrieve created end of a creation relation!");

    return getTargetEnd();
  }

  public Class getTerminationEvent() {
    return getTerminationEventEnd().getPropertyTypeAsClass();
  }

  public Property getTerminationEventEnd() {
    if (!isTermination())
      throw new IllegalCallerException(
          "Can only retrieve creation event end of a creation relation!");

    return getSourceEnd();
  }

  public Class getManifested() {
    return getManifestedEnd().getPropertyTypeAsClass();
  }

  public Property getManifestedEnd() {
    if (!isManifestation())
      throw new IllegalCallerException("Can only retrieve created end of a creation relation!");

    return getTargetEnd();
  }

  public Class getManifestation() {
    return getManifestationEnd().getPropertyTypeAsClass();
  }

  public Property getManifestationEnd() {
    if (!isManifestation())
      throw new IllegalCallerException(
          "Can only retrieve creation event end of a creation relation!");

    return getSourceEnd();
  }

  public Class getTriggered() {
    return getTriggeredEnd().getPropertyTypeAsClass();
  }

  public Property getTriggeredEnd() {
    if (!isTriggers())
      throw new IllegalCallerException("Can only retrieve created end of a creation relation!");

    return getTargetEnd();
  }

  public Class getTriggeringSituation() {
    return getTriggeringSituationEnd().getPropertyTypeAsClass();
  }

  public Property getTriggeringSituationEnd() {
    if (!isTriggers())
      throw new IllegalCallerException(
          "Can only retrieve creation event end of a creation relation!");

    return getSourceEnd();
  }

  public Class getOutcomeSituation() {
    return getOutcomeSituationEnd().getPropertyTypeAsClass();
  }

  public Property getOutcomeSituationEnd() {
    if (!isBringsAbout())
      throw new IllegalCallerException("Can only retrieve created end of a creation relation!");

    return getTargetEnd();
  }

  public Class getCause() {
    return getCauseEnd().getPropertyTypeAsClass();
  }

  public Property getCauseEnd() {
    if (!isBringsAbout())
      throw new IllegalCallerException(
          "Can only retrieve creation event end of a creation relation!");

    return getSourceEnd();
  }

  public Class getWhole() {
    return getWholeEnd().getPropertyTypeAsClass();
  }

  public Property getWholeEnd() {
    if (!isPartWholeRelation())
      throw new IllegalCallerException("Can only retrieve whole end of an externalDependence!");

    return getTargetEnd();
  }

  public Class getPart() {
    return getPartEnd().getPropertyTypeAsClass();
  }

  public Property getPartEnd() {
    if (!isPartWholeRelation())
      throw new IllegalCallerException("Can only retrieve part end of an externalDependence!");

    return getSourceEnd();
  }

  public Class getFunctionalComplex() {
    return getFunctionalComplexEnd().getPropertyTypeAsClass();
  }

  public Property getFunctionalComplexEnd() {
    if (!isComponentOf())
      throw new IllegalCallerException("Can only retrieve whole end of an externalDependence!");

    return getWholeEnd();
  }

  public Class getFunctionalPart() {
    return getFunctionalPartEnd().getPropertyTypeAsClass();
  }

  public Property getFunctionalPartEnd() {
    if (!isComponentOf())
      throw new IllegalCallerException("Can only retrieve part end of an externalDependence!");

    return getPartEnd();
  }

  public Class getCollectionWhole() {
    return getCollectionWholeEnd().getPropertyTypeAsClass();
  }

  public Property getCollectionWholeEnd() {
    if (!isMemberOf())
      throw new IllegalCallerException("Can only retrieve whole end of an externalDependence!");

    return getWholeEnd();
  }

  public Class getCollectionMember() {
    return getCollectionMemberEnd().getPropertyTypeAsClass();
  }

  public Property getCollectionMemberEnd() {
    if (!isMemberOf())
      throw new IllegalCallerException("Can only retrieve part end of an externalDependence!");

    return getPartEnd();
  }

  public Class getSuperCollection() {
    return getSuperCollectionEnd().getPropertyTypeAsClass();
  }

  public Property getSuperCollectionEnd() {
    if (!isSubCollectionOf())
      throw new IllegalCallerException("Can only retrieve whole end of an externalDependence!");

    return getWholeEnd();
  }

  public Class getSubCollection() {
    return getSubCollectionEnd().getPropertyTypeAsClass();
  }

  public Property getSubCollectionEnd() {
    if (!isSubCollectionOf())
      throw new IllegalCallerException("Can only retrieve part end of an externalDependence!");

    return getPartEnd();
  }

  public Class getSuperQuantity() {
    return getSuperQuantityEnd().getPropertyTypeAsClass();
  }

  public Property getSuperQuantityEnd() {
    if (!isSubCollectionOf())
      throw new IllegalCallerException("Can only retrieve whole end of an externalDependence!");

    return getWholeEnd();
  }

  public Class getSubQuantity() {
    return getSubQuantityEnd().getPropertyTypeAsClass();
  }

  public Property getSubQuantityEnd() {
    if (!isSubCollectionOf())
      throw new IllegalCallerException("Can only retrieve part end of an externalDependence!");

    return getPartEnd();
  }

  public Class getParticipationalWhole() {
    return getParticipationalWholeEnd().getPropertyTypeAsClass();
  }

  public Property getParticipationalWholeEnd() {
    if (!isParticipational())
      throw new IllegalCallerException(
          "Can only retrieve participational whole end of a participational part-whole relation!");

    return getWholeEnd();
  }

  public Class getParticipationalPart() {
    return getParticipationalPartEnd().getPropertyTypeAsClass();
  }

  public Property getParticipationalPartEnd() {
    if (!isParticipational())
      throw new IllegalCallerException(
          "Can only retrieve participational part end of a participational part-whole relation!");

    return getPartEnd();
  }

  public Class getCategorized() {
    return getCategorizedEnd().getPropertyTypeAsClass();
  }

  public Property getCategorizedEnd() {
    if (!isInstantiation())
      throw new IllegalCallerException(
          "Can only retrieve categorized end of an instantiation relation!");

    return getTargetEnd();
  }

  public Class getCategorizer() {
    return getCategorizerEnd().getPropertyTypeAsClass();
  }

  public Property getCategorizerEnd() {
    if (!isInstantiation())
      throw new IllegalCallerException(
          "Can only retrieve categorizer end of an instantiation relation!");

    return getSourceEnd();
  }
}

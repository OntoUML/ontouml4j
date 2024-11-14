package org.ontouml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.*;
import org.ontouml.model.Resource;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Element implements Comparable<Element> {
  String id;
  MultilingualText name;
  List<MultilingualText> alternativeNames;
  MultilingualText description;
  Date created;
  Date modified;

  List<MultilingualText> editorialNotes;

  List<Resource> creators;
  List<Resource> contributors;

  public Element(String id, MultilingualText name, List<MultilingualText> alternativeNames) {
    this(
        id,
        name,
        alternativeNames,
        new MultilingualText(),
        new Date(),
        null,
        new ArrayList<>(),
        new ArrayList<>(),
        new ArrayList<>());
  }

  public Element(
      String id,
      MultilingualText name,
      List<MultilingualText> alternativeNames,
      Date created,
      Date modified) {
    this(id, name, alternativeNames, null, created, modified, null, null, null);
  }

  public void setId(String id) {
    if (id == null) throw new NullPointerException("Cannot set null id.");

    this.id = id;
  }

  public Optional<String> getNameIn(String language) {
    return name.getText(language);
  }

  public Optional<String> getFirstName() {
    if (name == null) {
      return Optional.empty();
    }
    return name.getText();
  }

  public void addName(String languageTag, String value) {
    this.name.putText(languageTag, value);
  }

  public void addName(String value) {
    this.name.putText(value);
  }

  public void removeNameIn(String languageTag) {
    this.name.removeTextIn(languageTag);
  }

  public void removeAllNames() {
    this.name.removeAll();
  }

  public void addAlternativeName(MultilingualText alternativeName) {
    this.alternativeNames.add(alternativeName);
  }

  public void removeAllAlternativeNames() {
    this.alternativeNames = new ArrayList<>();
  }

  public Optional<String> getDescriptionIn(String language) {
    return description.getText(language);
  }

  public Optional<String> getFirstDescription() {
    return description.getText();
  }

  public void addDescription(String languageTag, String value) {
    this.description.putText(languageTag, value);
  }

  public void addDescription(String value) {
    this.description.putText(value);
  }

  public void removeDescription(String languageTag) {
    this.description.removeTextIn(languageTag);
  }

  public void removeAllDescriptions() {
    this.description.removeAll();
  }

  public Date getCreatedDate() {
    return this.created;
  }

  public Date getModifiedDate() {
    return this.modified;
  }

  @Override
  public int compareTo(Element element) {
    return this.getFirstName().orElse("").compareTo(element.getFirstName().orElse(""));
  }
}

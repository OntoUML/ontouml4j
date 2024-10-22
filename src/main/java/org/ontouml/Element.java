package org.ontouml;

import org.ontouml.model.Resource;

import java.util.*;

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

  public Element(String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames,
                 Date created,
                 Date modified,
                 List<MultilingualText> editorialNotes,
                 List<Resource> creators,
                 List<Resource> contributors) {
    this.id = id != null ? id : UUID.randomUUID().toString();
    this.name = name != null ? name : new MultilingualText();
    this.description = new MultilingualText();
    this.alternativeNames = alternativeNames;
    this.created = created;
    this.modified = modified;
    this.editorialNotes = editorialNotes == null ? new ArrayList<>() : editorialNotes;
    this.creators = creators == null ? new ArrayList<>() : creators;
    this.contributors = contributors == null ? new ArrayList<>() : contributors;
  }

  public Element(String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames,
                 Date created,
                 Date modified) {
    this(id, name, alternativeNames, created, modified, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  }

  public Element(String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames) {
    this(id, name, alternativeNames, new Date(), null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    if (id == null) throw new NullPointerException("Cannot set null id.");

    this.id = id;
  }

  public MultilingualText getName() {
    return this.name;
  }

  public Optional<String> getNameIn(String language) {
    return name.getText(language);
  }

  public Optional<String> getFirstName() {
    return name.getText();
  }

  public void setName(MultilingualText name) {
    this.name = name;
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

  public void setAlternativeNames(List<MultilingualText> alternativeNames) {
    this.alternativeNames = alternativeNames;
  }

  public void addAlternativeName(MultilingualText alternativeName) {
    this.alternativeNames.add(alternativeName);
  }

  public void removeAllAlternativeNames() {
    this.alternativeNames = new ArrayList<>();
  }

  public MultilingualText getDescription() {
    return this.description;
  }

  public Optional<String> getDescriptionIn(String language) {
    return description.getText(language);
  }

  public Optional<String> getFirstDescription() {
    return description.getText();
  }

  public void setDescription(MultilingualText description) {
    this.description = description;
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

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModifiedDate() {
    return this.modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public void setEditorialNotes(List<MultilingualText> editorialNotes) {
    this.editorialNotes = editorialNotes;
  }

  public void setCreators(List<Resource> creators) {
    this.creators = creators;
  }

  public void setContributors(List<Resource> contributors) {
    this.contributors = contributors;
  }

  public List<MultilingualText> getAlternativeNames() {
    return alternativeNames;
  }

  public List<MultilingualText> getEditorialNotes() {
    return editorialNotes;
  }

  public List<Resource> getCreators() {
    return creators;
  }

  public List<Resource> getContributors() {
    return contributors;
  }

  @Override
  public int compareTo(Element element) {
    return this.getFirstName().orElse("").compareTo(element.getFirstName().orElse(""));
  }
}

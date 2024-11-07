package org.ontouml;

import org.ontouml.model.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

  // Dor no plugin: Duas formas de manipular, dois estilos de programação diferente. Wrapper é interessante, mas talvez tem perda de performance. Decidir um estilo
  // TODO: Adicionar padrão Builder para montar objetos programaticamente
  public Element(String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames,
                 MultilingualText description,
                 Date created,
                 Date modified,
                 List<MultilingualText> editorialNotes,
                 List<Resource> creators,
                 List<Resource> contributors) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.alternativeNames = alternativeNames;
    this.created = created;
    this.modified = modified;
    this.editorialNotes = editorialNotes;
    this.creators = creators;
    this.contributors = contributors;
  }

  public Element(String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames,
                 MultilingualText description,
                 Date created,
                 Date modified) {
    this(id, name, alternativeNames, description, created, modified, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  }

  public Element(String id,
                 MultilingualText name,
                 List<MultilingualText> alternativeNames) {
    this(id, name, alternativeNames, new MultilingualText(), new Date(), null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
  }

  public Element(String id, MultilingualText name, List<MultilingualText> alternativeNames, Date created, Date modified) {
    this(id, name, alternativeNames, null, created, modified, null, null, null);
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

  public void setName(MultilingualText name) {
    this.name = name;
  }

  public Optional<String> getNameIn(String language) {
    return name.getText(language);
  }

  public Optional<String> getFirstName() {
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

  public MultilingualText getDescription() {
    return this.description;
  }

  public void setDescription(MultilingualText description) {
    this.description = description;
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

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModifiedDate() {
    return this.modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public List<MultilingualText> getAlternativeNames() {
    return alternativeNames;
  }

  public void setAlternativeNames(List<MultilingualText> alternativeNames) {
    this.alternativeNames = alternativeNames;
  }

  public List<MultilingualText> getEditorialNotes() {
    return editorialNotes;
  }

  public void setEditorialNotes(List<MultilingualText> editorialNotes) {
    this.editorialNotes = editorialNotes;
  }

  public List<Resource> getCreators() {
    return creators;
  }

  public void setCreators(List<Resource> creators) {
    this.creators = creators;
  }

  public List<Resource> getContributors() {
    return contributors;
  }

  public void setContributors(List<Resource> contributors) {
    this.contributors = contributors;
  }

  @Override
  public int compareTo(Element element) {
    return this.getFirstName().orElse("").compareTo(element.getFirstName().orElse(""));
  }
}

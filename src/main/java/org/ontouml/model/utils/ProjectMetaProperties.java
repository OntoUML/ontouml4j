package org.ontouml.model.utils;

import org.ontouml.model.MultilingualText;
import org.ontouml.model.Resource;

import java.net.URI;
import java.util.List;

public class ProjectMetaProperties {
  Resource publisher;
  List<Resource> designedForTasks;
  Resource license;
  List<Resource> accessRights;
  List<Resource> themes;
  List<Resource> contexts;
  List<Resource> ontologyTypes;
  Resource representationStyle;
  URI namespace;
  List<URI> landingPages;
  List<URI> sources;
  List<MultilingualText> bibliographicCitations;
  List<String> acronyms;
  List<String> languages;
  List<MultilingualText> keywords;

  public ProjectMetaProperties(
      Resource publisher,
      List<Resource> designedForTasks,
      Resource license,
      List<Resource> accessRights,
      List<Resource> themes,
      List<Resource> contexts,
      List<Resource> ontologyTypes,
      Resource representationStyle,
      URI namespace,
      List<URI> landingPages,
      List<URI> sources,
      List<MultilingualText> bibliographicCitations,
      List<String> acronyms,
      List<String> languages,
      List<MultilingualText> keywords) {
    this.publisher = publisher;
    this.designedForTasks = designedForTasks;
    this.license = license;
    this.accessRights = accessRights;
    this.themes = themes;
    this.contexts = contexts;
    this.ontologyTypes = ontologyTypes;
    this.representationStyle = representationStyle;
    this.namespace = namespace;
    this.landingPages = landingPages;
    this.sources = sources;
    this.bibliographicCitations = bibliographicCitations;
    this.acronyms = acronyms;
    this.languages = languages;
    this.keywords = keywords;
  }

  public ProjectMetaProperties() {
    super();
  }

  public Resource getPublisher() {
    return publisher;
  }

  public void setPublisher(Resource publisher) {
    this.publisher = publisher;
  }

  public List<Resource> getDesignedForTasks() {
    return designedForTasks;
  }

  public void setDesignedForTasks(List<Resource> designedForTasks) {
    this.designedForTasks = designedForTasks;
  }

  public Resource getLicense() {
    return license;
  }

  public void setLicense(Resource license) {
    this.license = license;
  }

  public List<Resource> getAccessRights() {
    return accessRights;
  }

  public void setAccessRights(List<Resource> accessRights) {
    this.accessRights = accessRights;
  }

  public List<Resource> getThemes() {
    return themes;
  }

  public void setThemes(List<Resource> themes) {
    this.themes = themes;
  }

  public List<Resource> getContexts() {
    return contexts;
  }

  public void setContexts(List<Resource> contexts) {
    this.contexts = contexts;
  }

  public List<Resource> getOntologyTypes() {
    return ontologyTypes;
  }

  public void setOntologyTypes(List<Resource> ontologyTypes) {
    this.ontologyTypes = ontologyTypes;
  }

  public Resource getRepresentationStyle() {
    return representationStyle;
  }

  public void setRepresentationStyle(Resource representationStyle) {
    this.representationStyle = representationStyle;
  }

  public URI getNamespace() {
    return namespace;
  }

  public void setNamespace(URI namespace) {
    this.namespace = namespace;
  }

  public List<URI> getLandingPages() {
    return landingPages;
  }

  public void setLandingPages(List<URI> landingPages) {
    this.landingPages = landingPages;
  }

  public List<URI> getSources() {
    return sources;
  }

  public void setSources(List<URI> sources) {
    this.sources = sources;
  }

  public List<MultilingualText> getBibliographicCitations() {
    return bibliographicCitations;
  }

  public void setBibliographicCitations(List<MultilingualText> bibliographicCitations) {
    this.bibliographicCitations = bibliographicCitations;
  }

  public List<String> getAcronyms() {
    return acronyms;
  }

  public void setAcronyms(List<String> acronyms) {
    this.acronyms = acronyms;
  }

  public List<String> getLanguages() {
    return languages;
  }

  public void setLanguages(List<String> languages) {
    this.languages = languages;
  }

  public List<MultilingualText> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<MultilingualText> keywords) {
    this.keywords = keywords;
  }
}

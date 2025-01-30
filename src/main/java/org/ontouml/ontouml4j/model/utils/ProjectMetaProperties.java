package org.ontouml.ontouml4j.model.utils;

import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.Resource;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}

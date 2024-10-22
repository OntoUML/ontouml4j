package org.ontouml.model;

import org.ontouml.MultilingualText;

import java.net.URI;

public class Resource {
  MultilingualText name;
  URI uri;

  public Resource(MultilingualText name, URI uri) {
    this.name = name;
    this.uri = uri;
  }
}

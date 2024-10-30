package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ontouml.MultilingualText;
import org.ontouml.deserialization.ResourceDeserializer;

import java.net.URI;

@JsonSerialize
@JsonDeserialize(using = ResourceDeserializer.class)
public class Resource {
  MultilingualText name;
  URI uri;

  public Resource(MultilingualText name, URI uri) {
//    super(UUID.randomUUID().toString(), name);
    this.name = name;
    this.uri = uri;
  }

  public Resource() {
  }

  public MultilingualText getName() {
    return name;
  }

  public void setName(MultilingualText name) {
    this.name = name;
  }

  public URI getUri() {
    return uri;
  }

  public void setUri(URI uri) {
    this.uri = uri;
  }

//  @Override
//  public List<OntoumlElement> getContents() {
//    return List.of();
//  }
//
//  @Override
//  public String getType() {
//    return "resource";
//  }
}

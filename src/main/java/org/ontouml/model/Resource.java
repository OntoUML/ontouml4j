package org.ontouml.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.ontouml.deserialization.ResourceDeserializer;
import org.ontouml.serialization.ResourceSerializer;

@Setter
@JsonSerialize(using = ResourceSerializer.class)
@JsonDeserialize(using = ResourceDeserializer.class)
@SuperBuilder
@Getter
public class Resource {
  MultilingualText name;
  URI uri;

  public Resource(MultilingualText name, URI uri) {
    this.name = name;
    this.uri = uri;
  }

  public Resource() {}
}

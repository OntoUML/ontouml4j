package org.ontouml.deserialization;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Resource;

import java.io.IOException;
import java.net.URI;

public class ResourceDeserializer extends JsonDeserializer<Resource> {
  @Override
  public Resource getNullValue(DeserializationContext ctxt) throws JsonMappingException {
    return null;
  }

  @Override
  public Resource deserialize(JsonParser parser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    ObjectCodec codec = parser.getCodec();
    JsonNode node = codec.readTree(parser);
    Resource resource = new Resource();

    if (node.isNull()) {
      return null;
    }

    JsonNode nameNode = node.get("name");
    if (nameNode != null) {
      MultilingualText name = nameNode.traverse(codec).readValueAs(MultilingualText.class);
      resource.setName(name);
    }
    JsonNode uriNode = node.get("URI");
    if (uriNode != null) {
      URI uri = uriNode.traverse(codec).readValueAs(URI.class);
      resource.setUri(uri);
    }

    if (uriNode == null && nameNode == null) {
      return null;
    }
    System.out.println(
        "Deserialized Resource name: " + resource.getName() + " " + resource.getUri());
    return resource;
  }
}

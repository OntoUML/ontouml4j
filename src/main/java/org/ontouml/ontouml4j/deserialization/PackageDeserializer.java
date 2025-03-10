package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.Package;
import org.ontouml.ontouml4j.model.PackageableElement;
import org.ontouml.ontouml4j.model.placeholders.UnresolvedPackageableElement;

public class PackageDeserializer extends JsonDeserializer<Package> {

  @Override
  public Package deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Package pkg = new Package();
    OntoumlElementDeserializer.deserialize(pkg, root, codec);
    NamedElementDeserializer.deserialize(pkg, root, codec);
    ModelElementDeserializer.deserialize(pkg, root, codec);
    deserializeContents(pkg, root, codec);

    return pkg;
  }

  private void deserializeContents(Package pkg, JsonNode root, ObjectCodec codec) {
    JsonNode contentsNode = root.get("contents");

    if (contentsNode != null && contentsNode.isArray()) {
      try {
        List<String> contentIds =
            contentsNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {});
        List<? extends PackageableElement> elements =
            contentIds.stream().map(UnresolvedPackageableElement::new).toList();
        pkg.addContents(elements);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

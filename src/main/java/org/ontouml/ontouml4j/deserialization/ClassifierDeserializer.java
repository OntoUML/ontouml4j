package org.ontouml.ontouml4j.deserialization;

import static org.ontouml.ontouml4j.deserialization.DeserializerUtils.deserializeBooleanField;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.List;
import org.ontouml.ontouml4j.model.Classifier;

public class ClassifierDeserializer {
  public static void deserialize(Classifier<?, ?> classifier, JsonNode root, ObjectCodec codec)
      throws IOException {

    boolean isAbstract = deserializeBooleanField(root, "isAbstract");
    classifier.setAbstract(isAbstract);

    boolean isDerived = deserializeBooleanField(root, "isDerived");
    classifier.setDerived(isDerived);

    JsonNode propertiesNode = root.get("properties");
    if (propertiesNode != null && propertiesNode.isArray()) {
      List<String> properties =
          propertiesNode.traverse(codec).readValueAs(new TypeReference<List<String>>() {});
      classifier.setProperties(properties);
    }
  }
}

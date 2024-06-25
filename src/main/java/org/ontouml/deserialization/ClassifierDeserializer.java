package org.ontouml.deserialization;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.ontouml.model.Classifier;
import org.ontouml.model.Property;

import java.io.IOException;
import java.util.List;

import static org.ontouml.deserialization.DeserializerUtils.deserializeBooleanField;

public class ClassifierDeserializer {

  public static void deserialize(Classifier<?, ?> classifier, JsonNode root, ObjectCodec codec)
      throws IOException {

    boolean isAbstract = deserializeBooleanField(root, "isAbstract");
    classifier.setAbstract(isAbstract);

    boolean isDerived = deserializeBooleanField(root, "isDerived");
    classifier.setDerived(isDerived);

    JsonNode propertiesNode = root.get("properties");
    if (propertiesNode != null && propertiesNode.isArray()) {
      List<Property> properties =
          propertiesNode.traverse(codec).readValueAs(new TypeReference<List<Property>>() {});
      classifier.setProperties(properties);
    }
  }
}

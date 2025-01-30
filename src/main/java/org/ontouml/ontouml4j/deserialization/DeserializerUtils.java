package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.ontouml.ontouml4j.model.*;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Package;
import org.ontouml.ontouml4j.model.view.*;
import org.ontouml.ontouml4j.shape.Path;
import org.ontouml.ontouml4j.shape.Rectangle;
import org.ontouml.ontouml4j.shape.Text;

public class DeserializerUtils {

  public static boolean deserializeBooleanField(JsonNode containerNode, String fieldname) {
    JsonNode booleanNode = containerNode.get(fieldname);

    if (booleanNode != null && booleanNode.isBoolean()) {
      return booleanNode.asBoolean();
    }

    return false;
  }

  public static String deserializeNullableStringField(JsonNode containerNode, String fieldname) {
    JsonNode stringNode = containerNode.get(fieldname);

    if (stringNode != null && stringNode.isTextual()) {
      return stringNode.asText();
    }

    return null;
  }

  public static String[] deserializeNullableStringArrayField(
      JsonNode containerNode, String fieldname, ObjectCodec codec) throws IOException {
    JsonNode arrayNode = containerNode.get(fieldname);

    if (arrayNode != null && arrayNode.isArray()) {
      return arrayNode.traverse(codec).readValueAs(String[].class);
    }

    return new String[0];
  }

  public static Boolean deserializeNullableBooleanField(JsonNode containerNode, String fieldname) {
    JsonNode booleanNode = containerNode.get(fieldname);

    if (booleanNode != null && booleanNode.isBoolean()) {
      return booleanNode.asBoolean();
    }

    return null;
  }

  public static Integer deserializeNullableIntegerField(JsonNode containerNode, String fieldname) {
    JsonNode integerNode = containerNode.get(fieldname);

    if (integerNode != null && integerNode.canConvertToInt()) {
      return integerNode.asInt();
    }

    return null;
  }

  public static Double deserializeNullableDoubleField(JsonNode containerNode, String fieldname) {
    JsonNode doubleNode = containerNode.get(fieldname);

    if (doubleNode != null && doubleNode.isNumber()) {
      return doubleNode.asDouble();
    }

    return null;
  }

  public static boolean isReferenceOf(JsonNode node, java.lang.Class<?> referenceType) {
    if (!node.isObject()) return false;

    String typeFieldValue = node.get("type").asText();

    if (referenceType.equals(Class.class)) return "Class".equals(typeFieldValue);
    else if (referenceType.equals(Relation.class)) return "Relation".equals(typeFieldValue);
    else if (referenceType.equals(Property.class)) return "Property".equals(typeFieldValue);
    else if (referenceType.equals(Generalization.class))
      return "Generalization".equals(typeFieldValue);
    else if (referenceType.equals(GeneralizationSet.class))
      return "GeneralizationSet".equals(typeFieldValue);
    else if (referenceType.equals(Package.class)) return "Package".equals(typeFieldValue);
    else if (referenceType.equals(Literal.class)) return "Literal".equals(typeFieldValue);
    else if (referenceType.equals(Project.class)) return "Project".equals(typeFieldValue);
    else if (referenceType.equals(ClassView.class)) return "ClassView".equals(typeFieldValue);
    else if (referenceType.equals(BinaryRelationView.class))
      return "BinaryRelationView".equals(typeFieldValue);
    else if (referenceType.equals(NaryRelationView.class))
      return "NaryRelationView".equals(typeFieldValue);
    else if (referenceType.equals(GeneralizationView.class))
      return "GeneralizationView".equals(typeFieldValue);
    else if (referenceType.equals(GeneralizationSetView.class))
      return "GeneralizationSetView".equals(typeFieldValue);
    else if (referenceType.equals(PackageView.class)) return "PackageView".equals(typeFieldValue);
    else if (referenceType.equals(Path.class)) return "Path".equals(typeFieldValue);
    else if (referenceType.equals(Rectangle.class)) return "Rectangle".equals(typeFieldValue);
    else if (referenceType.equals(Text.class)) return "Text".equals(typeFieldValue);

    return false;
  }

  public static java.lang.Class<? extends OntoumlElement> getClass(String typeName) {
    return switch (typeName) {
      case "ClassView" -> ClassView.class;
      case "BinaryRelationView" -> BinaryRelationView.class;
      case "NaryRelationView" -> NaryRelationView.class;
      case "GeneralizationView" -> GeneralizationView.class;
      case "GeneralizationSetView" -> GeneralizationSetView.class;
      case "PackageView" -> PackageView.class;
      case "Path" -> Path.class;
      case "Rectangle" -> Rectangle.class;
      case "Text" -> Text.class;
      case "Class" -> Class.class;
      case "Relation" -> Relation.class;
      case "Generalization" -> Generalization.class;
      case "GeneralizationSet" -> GeneralizationSet.class;
      case "Package" -> Package.class;
      case "Property" -> Property.class;
      case "Literal" -> Literal.class;
      case "Project" -> Project.class;
      case "Diagram" -> Diagram.class;
      default -> null;
    };
  }

  /**
   * Deserializes a node into the subclass of {@link OntoumlElement} that matches the "type" field
   * of the JSON object
   */
  private static OntoumlElement deserializeObject(
      JsonNode node,
      List<java.lang.Class<? extends OntoumlElement>> allowedTypes,
      ObjectCodec codec)
      throws IOException {

    boolean isObject = node.isObject();
    if (!node.isObject()) return null;

    JsonNode typeNode = node.get("type");
    if (typeNode == null || !typeNode.isTextual()) return null;

    String typeNodeValue = typeNode.asText();
    java.lang.Class<? extends OntoumlElement> type = getClass(typeNodeValue);

    if (allowedTypes.contains(type)) {
      return node.traverse(codec).readValueAs(type);
    }

    throw new JsonParseException(
        codec.treeAsTokens(node), "Cannot deserialize object! Wrong type.");
  }

  private static <T extends OntoumlElement> T deserializeObject(
      JsonNode node, java.lang.Class<T> allowedType, ObjectCodec codec) throws IOException {

    OntoumlElement element = deserializeObject(node, List.of(allowedType), codec);
    return castOrNull(element, allowedType);
  }

  public static OntoumlElement deserializeObjectField(
      JsonNode containerNode,
      String fieldName,
      List<java.lang.Class<? extends OntoumlElement>> allowedTypes,
      ObjectCodec codec)
      throws IOException {

    JsonNode node = containerNode.get(fieldName);
    return deserializeObject(node, allowedTypes, codec);
  }

  public static <T extends OntoumlElement> T deserializeObjectField(
      JsonNode containerNode, String fieldName, java.lang.Class<T> allowedType, ObjectCodec codec)
      throws IOException {

    JsonNode node = containerNode.get(fieldName);
    return deserializeObject(node, allowedType, codec);
  }

  public static List<OntoumlElement> deserializeArrayField(
      JsonNode containerNode,
      String fieldName,
      List<java.lang.Class<? extends OntoumlElement>> allowedTypes,
      ObjectCodec codec)
      throws IOException {

    JsonNode arrayNode = containerNode.get(fieldName);

    if (arrayNode == null || arrayNode.isNull()) {
      return List.of();
    }

    if (arrayNode.isArray()) {
      List<OntoumlElement> list = new ArrayList<>();
      Iterator<JsonNode> iterator = arrayNode.elements();

      while (iterator.hasNext()) {
        JsonNode memberNode = iterator.next();
        OntoumlElement member = deserializeObject(memberNode, allowedTypes, codec);
        if (member != null) list.add(member);
      }

      return list;
    }

    throw new JsonParseException(codec.treeAsTokens(arrayNode), "Cannot deserialize object array!");
  }

  public static <T extends OntoumlElement> List<T> deserializeArrayField(
      JsonNode containerNode, String fieldName, java.lang.Class<T> allowedType, ObjectCodec codec)
      throws IOException {

    JsonNode arrayNode = containerNode.get(fieldName);

    if (arrayNode == null || arrayNode.isNull()) {
      return List.of();
    }

    if (arrayNode.isArray()) {
      List<T> list = new ArrayList<>();
      Iterator<JsonNode> iterator = arrayNode.elements();

      while (iterator.hasNext()) {
        JsonNode memberNode = iterator.next();
        T member = deserializeObject(memberNode, allowedType, codec);
        if (member != null) list.add(member);
      }

      return list;
    }

    throw new JsonParseException(codec.treeAsTokens(arrayNode), "Cannot deserialize object array!");
  }

  public static Classifier<?, ?> deserializeClassifierField(
      JsonNode containerNode, String fieldName, ObjectCodec codec) throws IOException {

    String classifier = deserializeNullableStringField(containerNode, fieldName);

    return castOrNull(classifier, Classifier.class);
  }

  public static <T> T castOrNull(Object object, java.lang.Class<T> type) {
    return (type.isInstance(object)) ? type.cast(object) : null;
  }
}

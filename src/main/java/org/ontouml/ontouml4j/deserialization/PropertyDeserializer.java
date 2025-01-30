package org.ontouml.ontouml4j.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import org.ontouml.ontouml4j.model.Property;
import org.ontouml.ontouml4j.model.utils.AggregationKind;

public class PropertyDeserializer extends JsonDeserializer<Property> {

  @Override
  public Property deserialize(JsonParser parser, DeserializationContext context)
      throws IOException {
    ObjectCodec codec = parser.getCodec();
    JsonNode root = parser.readValueAsTree();

    Property property = new Property();
    OntoumlElementDeserializer.deserialize(property, root, codec);
    NamedElementDeserializer.deserialize(property, root, codec);
    ModelElementDeserializer.deserialize(property, root, codec);
    DecoratableDeserializer.deserialize(property, root, codec);

    boolean isReadOnly = DeserializerUtils.deserializeBooleanField(root, "isReadOnly");
    property.setReadOnly(isReadOnly);

    boolean isOrdered = DeserializerUtils.deserializeBooleanField(root, "isOrdered");
    property.setOrdered(isOrdered);

    String cardinality = DeserializerUtils.deserializeNullableStringField(root, "cardinality");
    if (cardinality != null) {
      property.setCardinality(cardinality);
    }

    String propertyType = DeserializerUtils.deserializeNullableStringField(root, "propertyType");
    property.setPropertyTypeId(propertyType);

    String[] subsettedProperties =
        DeserializerUtils.deserializeNullableStringArrayField(root, "subsettedProperties", codec);
    property.setSubsettedProperties(subsettedProperties);

    String[] redefinedProperties =
        DeserializerUtils.deserializeNullableStringArrayField(root, "redefinedProperties", codec);
    property.setRedefinedProperties(redefinedProperties);

    String aggregationKind =
        DeserializerUtils.deserializeNullableStringField(root, "aggregationKind");
    property.setAggregationKind(AggregationKind.findByName(aggregationKind).orElse(null));

    return property;
  }
}

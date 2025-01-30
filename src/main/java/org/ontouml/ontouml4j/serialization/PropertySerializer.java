package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Optional;
import org.ontouml.ontouml4j.model.Classifier;
import org.ontouml.ontouml4j.model.Property;
import org.ontouml.ontouml4j.model.utils.AggregationKind;

public class PropertySerializer extends JsonSerializer<Property> {

  public static void serializeFields(Property property, JsonGenerator jsonGen) throws IOException {
    jsonGen.writeStringField("type", "Property");
    DecoratableSerializer.serializeFields(property, jsonGen);
    jsonGen.writeBooleanField("isDerived", property.isDerived());
    jsonGen.writeBooleanField("isOrdered", property.isOrdered());
    jsonGen.writeBooleanField("isReadOnly", property.isReadOnly());

    Optional<Classifier<?, ?>> propertyType = property.getPropertyType();
    if (propertyType.isEmpty()) {
      jsonGen.writeNullField("propertyType");
    } else {

      Serializer.writeNullableStringField("propertyType", propertyType.get().getId(), jsonGen);
    }

    Optional<AggregationKind> aggregationKind = property.getAggregationKind();
    if (propertyType.isEmpty()) {
      jsonGen.writeNullField("aggregationKind");
    } else {

      Serializer.writeNullableStringField("aggregationKind", propertyType.get().getId(), jsonGen);
    }

    Serializer.writeEmptyableArrayField(
        "subsettedProperties", property.getSubsettedProperties(), jsonGen);
    Serializer.writeEmptyableArrayField(
        "redefinedProperties", property.getRedefinedProperties(), jsonGen);
    Serializer.writeNullableStringField(
        "cardinality", property.getCardinality().getCardinality(), jsonGen);
  }

  @Override
  public void serialize(Property property, JsonGenerator jsonGen, SerializerProvider serializers)
      throws IOException {
    jsonGen.writeStartObject();
    serializeFields(property, jsonGen);
    jsonGen.writeEndObject();
  }
}

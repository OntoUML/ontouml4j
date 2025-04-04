package org.ontouml.ontouml4j.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.util.Collection;

public class Serializer {
  public static void writeNullableStringField(String fieldName, String value, JsonGenerator jsonGen)
      throws IOException {

    if (value != null) jsonGen.writeStringField(fieldName, value);
    else jsonGen.writeNullField(fieldName);
  }

  public static void writeNullableBooleanField(
      String fieldName, Boolean value, JsonGenerator jsonGen) throws IOException {

    if (value != null) jsonGen.writeBooleanField(fieldName, value);
    else jsonGen.writeNullField(fieldName);
  }

  public static void writeNullableNumberField(String fieldName, Number value, JsonGenerator jsonGen)
      throws IOException {

    if (value == null) {
      jsonGen.writeNullField(fieldName);
      return;
    }

    if (value instanceof Integer) jsonGen.writeNumberField(fieldName, (Integer) value);
    else if (value instanceof Double) jsonGen.writeNumberField(fieldName, (Double) value);
    else if (value instanceof Long) jsonGen.writeNumberField(fieldName, (Long) value);
    else if (value instanceof Float) jsonGen.writeNumberField(fieldName, (Float) value);
  }

  public static void writeNullableOjectField(String fieldname, Object value, JsonGenerator jsonGen)
      throws IOException {
    if (value == null) {
      jsonGen.writeNullField(fieldname);
    } else {
      jsonGen.writeObjectField(fieldname, value);
    }
  }

  public static void writeNullableArrayField(
      String fieldName, Collection<?> list, JsonGenerator jsonGen) throws IOException {

    if (list != null && !list.isEmpty()) jsonGen.writeObjectField(fieldName, list);
    else jsonGen.writeNullField(fieldName);
  }

  public static void writeEmptyableArrayField(
      String fieldName, Collection<?> list, JsonGenerator jsonGen) throws IOException {
    if (list != null && !list.isEmpty()) jsonGen.writeObjectField(fieldName, list);
    else {
      jsonGen.writeArrayFieldStart(fieldName);
      jsonGen.writeEndArray();
    }
  }
}

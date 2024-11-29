package org.ontouml.deserialization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.ontouml.utils.ResourceGetter;

public class ValidationTest {

  static Schema schema;

  @BeforeAll
  static void setUp() throws IOException {
    String schemaPath =
        Paths.get(
                System.getProperty("user.dir"),
                "src",
                "test",
                "java",
                "org",
                "ontouml",
                "deserialization",
                "resources",
                "ontouml-schema.json")
            .toAbsolutePath()
            .toString();
    String schemaContent = new String(Files.readAllBytes(Paths.get(schemaPath)));
    JSONObject rawSchema = new JSONObject(new JSONTokener(schemaContent));
    SchemaLoader loader = SchemaLoader.builder().schemaJson(rawSchema).draftV7Support().build();
    schema = loader.load().build();
  }

  static void printValidationException(ValidationException exception) {
    for (int i = 0; i < exception.getCausingExceptions().size(); i++) {
      System.out.println("Validation error " + i);
      System.out.println();
    }
    exception.getCausingExceptions().stream()
        .peek((item) -> System.out.println("Validation error: "))
        .map(ValidationException::getMessage)
        .forEach(System.out::println);
    exception
        .getCausingExceptions()
        .forEach(
            excp -> {
              System.out.println("Keyword: " + excp.getKeyword());
              System.out.println("Location in Schema: " + excp.getSchemaLocation());
            });
  }

  @Test
  void testSchemaOnProject() throws IOException {
    File file = ResourceGetter.getJsonFromDeserialization("project.allfields.ontouml.json");
    JSONObject json = new JSONObject(new JSONTokener(Files.newBufferedReader(file.toPath())));
    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  @Test
  void testSchemaOnAnchor() throws IOException {
    File file = ResourceGetter.getJsonFromDeserialization("anchor.allfields.ontouml.json");
    JSONObject json = new JSONObject(new JSONTokener(Files.newBufferedReader(file.toPath())));
    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  @Test
  void testSchemaOnClass() throws IOException {
    File file = ResourceGetter.getJsonFromDeserialization("class.allfields.ontouml.json");
    JSONObject json = new JSONObject(new JSONTokener(Files.newBufferedReader(file.toPath())));
    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  @Test
  void testSchemaOnClassMinimum() throws IOException {
    File file = ResourceGetter.getJsonFromDeserialization("class.minimum.ontouml.json");
    JSONObject json = new JSONObject(new JSONTokener(Files.newBufferedReader(file.toPath())));
    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  @Test
  void testSchemaOnPackage() throws IOException {
    File file = ResourceGetter.getJsonFromDeserialization("package.allfields.ontouml.json");
    JSONObject json = new JSONObject(new JSONTokener(Files.newBufferedReader(file.toPath())));
    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  @Test
  void testSchemaOnProperty() throws IOException {
    File file = ResourceGetter.getJsonFromDeserialization("property.minimum.ontouml.json");
    JSONObject json = new JSONObject(new JSONTokener(Files.newBufferedReader(file.toPath())));
    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  @Test
  void testSchemaOnRelation() throws IOException {
    File file = ResourceGetter.getJsonFromDeserialization("relation.allfields.ontouml.json");
    JSONObject json = new JSONObject(new JSONTokener(Files.newBufferedReader(file.toPath())));
    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }
}

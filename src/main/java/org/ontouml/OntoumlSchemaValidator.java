package org.ontouml;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.ontouml.model.Project;

public class OntoumlSchemaValidator {
  static Schema schema;
  static String schemaPath =
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

  public static void validate(Project project) throws IOException {
    setUp();
    String json = project.serializeAsString();

    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  public static void validate(String json) throws IOException {
    setUp();

    try {
      schema.validate(json);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  public static void validate(JsonNode root) throws IOException {
    setUp();

    try {
      schema.validate(root);
      System.out.println("This JSON is compliant with OntoUML Schema");
    } catch (ValidationException e) {
      printValidationException(e);
      throw e;
    }
  }

  private static void setUp() throws IOException {

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
}

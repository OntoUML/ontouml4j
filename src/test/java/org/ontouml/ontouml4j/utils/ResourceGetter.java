package org.ontouml.ontouml4j.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceGetter {
  static Path deserializationPath =
      Paths.get(
          System.getProperty("user.dir"),
          "src",
          "test",
          "java",
          "org",
          "ontouml",
          "ontouml4j",
          "deserialization",
          "resources");
  static Path serializationPath =
      Paths.get(
          System.getProperty("user.dir"),
          "src",
          "test",
          "java",
          "org",
          "ontouml",
          "ontouml4j",
          "serialization",
          "resources");

  public static File getJsonFromSerialization(String jsonName) {
    String jsonFilePath = serializationPath + File.separator + jsonName;

    return new File(jsonFilePath);
  }

  public static File getJsonFromDeserialization(String jsonName) {
    String jsonFilePath = deserializationPath + File.separator + jsonName;

    return new File(jsonFilePath);
  }
}

package org.ontouml.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceGetter {
  Path deserializationPath = Paths.get(System.getProperty("user.dir"),"src", "test","java", "org", "ontouml", "deserialization", "resources");
  Path serializationPath = Paths.get(System.getProperty("user.dir"),"src", "test","java", "org", "ontouml", "serialization", "resources");


  public File getJsonFromSerialization(String jsonName) {
    String jsonFilePath = serializationPath + File.separator + jsonName;

    return new File(jsonFilePath);
  }

  public File getJsonFromDeserialization(String jsonName) {
    String jsonFilePath = deserializationPath + File.separator + jsonName;

    return new File(jsonFilePath);
  }
}

package org.ontouml.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.ontouml.model.*;
import org.ontouml.model.Class;
import org.ontouml.model.Package;

public class SerializerTest {
  ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  @Test
  void buildAndSerializeModel() throws IOException {
    Project project = new Project();
    project.addName("pt", "Meu Project");
    project.addName("en", "My Project");
    project.addDescription("it", "Il miglior progetto in modellazione concettuale.");
    project.addDescription("en", "The best conceptual modeling project.");

    Package model = project.createPackage("pk1", "My Model");
    Package pkg = model.createPackage("pk2", "My Module");

    Class agent = model.createCategory("c1", "Agent");
    Class person = model.createKind("c2", "Person");
    Class organization = model.createKind("c3", "Organization");
    Class agentType = model.createType("c4", "AgentType");

    Class car = pkg.createKind("Car");
    Class string = pkg.createDatatype("string");
    Class number = pkg.createDatatype("number");

    Property name = agent.createAttribute("name", string);
    name.setCardinality("1..*");

    Property birthName = person.createAttribute("birthName", string);
    birthName.addRedefinedProperty(name);

    Property nickname = person.createAttribute("nickname", string);
    nickname.addSubsettedProperty(name);

    Property age = person.createAttribute("age", number);
    age.setCardinality("1");

    car.addCustomProperty("uri", "https://schema.org/Car");
    car.addCustomProperty("score", 10);
    car.addCustomProperty("value", 8.9);
    car.addCustomProperty("isLiked", false);
    car.addCustomProperty("author", new String[] {"Tiago", "Davi"});
    car.addCustomProperty("source", null);

    BinaryRelation owns = model.createRelation("owns", person, car);
    owns.getSourceEnd().setCardinality("1..*");
    owns.getTargetEnd().setCardinality("0..*");

    Generalization g1 = model.createGeneralization("g1", person, agent);
    Generalization g2 = model.createGeneralization("g2", organization, agent);

    mapper.writeValue(new File("target/serializedModel.json"), project);
  }
}

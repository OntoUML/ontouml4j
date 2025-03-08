package org.ontouml.ontouml4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.URISyntaxException;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.Nature;
import org.ontouml.ontouml4j.model.Package;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.model.stereotype.ClassStereotype;

public class Main {
  public static void main(String[] args) throws URISyntaxException, JsonProcessingException {
    Project myProject = new Project("project_1", "My Project");

    Package myPackage = myProject.createPackage("package_1", "My Package name");

    Class clazz = myPackage.createClass("class_1", "My Class name", ClassStereotype.KIND);

    myProject.serializeAsString();

    clazz.setRestrictedTo(Nature.COLLECTIVE);
  }
}

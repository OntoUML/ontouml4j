package org.ontouml.ontouml4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import org.ontouml.ontouml4j.model.Class;
import org.ontouml.ontouml4j.model.MultilingualText;
import org.ontouml.ontouml4j.model.Project;
import org.ontouml.ontouml4j.model.Resource;
import org.ontouml.ontouml4j.model.utils.ProjectMetaProperties;

public class Main {
  public static void main(String[] args) throws URISyntaxException {
    Project myProject = createProject();

    System.out.println(myProject.getName());
    System.out.println(myProject);

    // TODO: Validar JSON Schema com biblioteca
  }

  private static Project createProject() throws URISyntaxException {
    try {
      ProjectMetaProperties metaProperties =
          ProjectMetaProperties.builder()
              .publisher(
                  Resource.builder()
                      .uri(new URI("https://org.ontouml"))
                      .name(new MultilingualText("OntoUML Foundation"))
                      .build())
              .build();

      List<Class> classes = createClasses();

      return Project.builder()
          .id("project_1")
          .name(new MultilingualText("My Project"))
          .description(new MultilingualText("Project Description."))
          .created(new Date())
          .alternativeNames(List.of(new MultilingualText("A second name for my Project")))
          .metaProperties(metaProperties)
          .build();

    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static List<Class> createClasses() {
    return List.of(
        Class.builder()
            .id("class_1")
            .name(new MultilingualText("Person"))
            .description(new MultilingualText("A person."))
            .build(),
        Class.builder()
            .id("class_2")
            .name(new MultilingualText("Organization"))
            .description(new MultilingualText("An organization."))
            .build());
  }
}

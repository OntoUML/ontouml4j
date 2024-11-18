package org.ontouml;

import java.util.List;
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Project;

public class Main {
  public static void main(String[] args) {
    Project myProject =
        Project.builder()
            .id("12345")
            .name(new MultilingualText("Project Name"))
            .description(new MultilingualText("Project Description"))
            .elements(List.of("Element1", "Element2"))
            .build();

    System.out.println(myProject.getName());
    System.out.println(myProject.getId());
  }
}

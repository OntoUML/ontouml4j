package org.ontouml;

import org.ontouml.model.Project;
import org.ontouml.model.MultilingualText;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    Project myProject = Project.builder()
            .id("12345")
            .name(new MultilingualText("Project Name"))
            .description(new MultilingualText("Project Description"))
            .elements(List.of("Element1", "Element2"))
            .build();

    System.out.println(myProject.getName());
    System.out.println(myProject.getId());
  }
}
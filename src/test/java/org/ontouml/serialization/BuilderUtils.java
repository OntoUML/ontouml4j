package org.ontouml.serialization;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.ontouml.model.*;
import org.ontouml.model.Class;
import org.ontouml.model.Package;
import org.ontouml.model.stereotype.ClassStereotype;
import org.ontouml.model.utils.AggregationKind;

class BuilderUtils {
  static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

  static Project createProject() throws URISyntaxException {
    var projectName = new MultilingualText("My Project");
    projectName.putText("pt-br", "Meu Projeto");
    Date modifiedDate =
        Date.from(ZonedDateTime.parse("2024-09-04T00:00:00Z", formatter).toInstant());
    Resource creator =
        new Resource(new MultilingualText("Matheus Lenke"), new URI("https://google.com"));
    MultilingualText description =
        new MultilingualText(
            Map.of(
                "en",
                "The best conceptual modeling project.",
                "it",
                "Il miglior progetto in modellazione concettuale."));

    Map<String, Package> packages = createPackages();

    Project project =
        Project.builder()
            .id("project_1")
            .created(new Date())
            .name(projectName)
            .modified(modifiedDate)
            .description(description)
            .alternativeNames(List.of(new MultilingualText("Project first alternative name")))
            .classes(createClasses())
            //        .properties(createProperties())
            //        .literals(createLiterals())
            //            .relations(createRelations())
            .creators(List.of(creator))
            .packages(packages)
            .build();
    packages.forEach(
        (key, item) -> {
          item.setProjectContainer(project);
        });
    return project;
  }

  static Map<String, Package> createPackages() throws URISyntaxException {
    Map<String, Package> packages = new HashMap<>();

    Date createdDate =
        Date.from(ZonedDateTime.parse("2024-09-03T00:00:00Z", formatter).toInstant());
    Date modifiedDate =
        Date.from(ZonedDateTime.parse("2024-09-04T00:00:00Z", formatter).toInstant());

    List<PackageableElement> contents = new ArrayList<>();
    contents.addAll(createClasses().values());
    contents.addAll(createProperties().values());

    Package package1 =
        Package.builder()
            .id("package_1")
            .name(new MultilingualText("My Package"))
            .description(new MultilingualText("My Project description."))
            .created(createdDate)
            .modified(modifiedDate)
            .customProperties(Map.of("myProperty", "My Value"))
            .contents(contents)
            .creators(createCreators())
            .editorialNotes(createEditorialNotes())
            .build();

    packages.put(package1.getId(), package1);
    return packages;
  }

  static Map<String, Class> createClasses() throws URISyntaxException {
    Map<String, Class> classes = new HashMap<>();

    Class class1 =
        Class.builder()
            .name(new MultilingualText("class1"))
            .id("class_1")
            .created(new Date())
            .modified(new Date())
            .alternativeNames(List.of(new MultilingualText("Class first alternative name")))
            .description(
                new MultilingualText(Map.of("en", "The best class.", "it", "La migliore classe.")))
            .properties(createProperties().values().stream().toList())
            .literals(createLiterals().values().stream().toList())
            .isAbstract(true)
            .isDerived(true)
            .isPowertype(true)
            .order("1")
            .restrictedTo(List.of(Nature.FUNCTIONAL_COMPLEX, Nature.COLLECTIVE))
            .ontoumlStereotype(ClassStereotype.KIND)
            .creators(createCreators())
            .editorialNotes(createEditorialNotes())
            .isAbstract(true)
            .build();

    Class class2 =
        Class.builder()
            .name(new MultilingualText("class2"))
            .id("class_2")
            .ontoumlStereotype(ClassStereotype.KIND)
            .isAbstract(true)
            .build();

    classes.put(class1.getId(), class1);
    classes.put(class2.getId(), class2);
    return classes;
  }

  static Map<String, Property> createProperties() {
    Map<String, Property> properties = new HashMap<>();
    Property property1 =
        Property.builder()
            .name(new MultilingualText("My Property1"))
            .cardinality(new Cardinality("1", "*"))
            .isDerived(true)
            .isOrdered(true)
            .isReadOnly(true)
            .aggregationKind(AggregationKind.COMPOSITE)
            .build();
    properties.put("property1", property1);
    return properties;
  }

  static Map<String, Relation> createRelations() {
    Map<String, Relation> relations = new HashMap<>();
    Relation relation1 =
        BinaryRelation.builder().properties(createProperties().values().stream().toList()).build();
    relations.put("relation1", relation1);
    return relations;
  }

  static Map<String, Literal> createLiterals() {
    Map<String, Literal> literals = new HashMap<>();
    literals.put("literal1", new Literal("literal1"));
    return literals;
  }

  static List<Resource> createCreators() throws URISyntaxException {
    return List.of(
        new Resource(new MultilingualText("Matheus Lenke"), new URI("https://lenke.software")));
  }

  static List<MultilingualText> createEditorialNotes() throws URISyntaxException {
    return List.of(new MultilingualText("Editorial Note 1"));
  }
}

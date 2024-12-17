package org.ontouml.utils;

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
import org.ontouml.model.utils.ProjectMetaProperties;

// TODO: Create small project for each test
public class BuilderUtils {
  static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

  public static Project createProject() throws URISyntaxException {
    var projectName = new MultilingualText(Map.of("en", "My Project", "pt", "Meu Projeto"));
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

    Map<String, Generalization> generalizations = createGeneralizations();

    Map<String, GeneralizationSet> generalizationSets = createGeneralizationSets();

    Map<String, Class> classes = createClasses();

    generalizations.forEach(
        (key, item) -> {
          Class general = classes.get("class_1");
          Class specific = classes.get("class_2");
          item.setGeneral(general);
          item.setSpecific(specific);
        });

    generalizationSets.forEach(
        (key, item) -> {
          item.setGeneralizations(generalizations.values());
        });

    var notes = createNotes();
    var anchors = createAnchors();

    anchors.forEach(
        (key, item) -> {
          item.setNote(notes.get("note_1"));
          item.setElement(classes.get("class_1"));
        });

    Project project =
        Project.builder()
            .id("project_1")
            .created(new Date())
            .name(projectName)
            .modified(modifiedDate)
            .description(description)
            .alternativeNames(List.of(new MultilingualText("Project first alternative name")))
            .classes(classes)
            .properties(createProperties())
            .generalizations(generalizations)
            .generalizationSets(generalizationSets)
            .literals(createLiterals())
            .relations(createRelations())
            .packages(packages)
            .notes(notes)
            .anchors(anchors)
            .creators(List.of(creator))
            .build();
    packages.forEach(
        (key, item) -> {
          item.setProjectContainer(project);
        });
    return project;
  }

  public static ProjectMetaProperties createProjectMetaProperties() {
    return new ProjectMetaProperties();
  }

  public static Map<String, Package> createPackages() throws URISyntaxException {
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

  public static Map<String, Class> createClasses() throws URISyntaxException {
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

  public static Map<String, Property> createProperties() {
    Map<String, Property> properties = new HashMap<>();
    Property property1 =
        Property.builder()
            .id("property_1")
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

  public static Map<String, Relation> createRelations() {
    Map<String, Relation> relations = new HashMap<>();
    Relation relation1 =
        BinaryRelation.builder()
            .id("relation_1")
            .properties(createProperties().values().stream().toList())
            .build();
    relations.put("relation1", relation1);
    return relations;
  }

  public static Map<String, Literal> createLiterals() {
    Map<String, Literal> literals = new HashMap<>();
    literals.put("literal1", new Literal("literal1"));
    return literals;
  }

  public static List<Resource> createCreators() throws URISyntaxException {
    return List.of(
        new Resource(new MultilingualText("Matheus Lenke"), new URI("https://lenke.software")));
  }

  public static List<MultilingualText> createEditorialNotes() throws URISyntaxException {
    return List.of(new MultilingualText("Editorial Note 1"));
  }

  public static Map<String, Generalization> createGeneralizations() {
    Map<String, Generalization> generalizations = new HashMap<>();
    Generalization generalization1 = Generalization.builder().id("generalization_1").build();
    Generalization generalization2 = Generalization.builder().id("generalization_2").build();
    generalizations.put("generalization_1", generalization1);
    generalizations.put("generalization_2", generalization2);
    return generalizations;
  }

  public static Map<String, GeneralizationSet> createGeneralizationSets() {
    Map<String, GeneralizationSet> generalizationSets = new HashMap<>();
    GeneralizationSet generalizationSet1 =
        GeneralizationSet.builder().id("generalizationSet_1").build();
    generalizationSets.put("generalizationSet_1", generalizationSet1);
    return generalizationSets;
  }

  public static Map<String, Note> createNotes() {
    Map<String, Note> notes = new HashMap<>();
    Note note1 =
        Note.builder()
            .id("note_1")
            .text(new MultilingualText(Map.of("en", "My Note Text", "pt", "Meu texto")))
            .build();
    notes.put("note_1", note1);
    return notes;
  }

  public static Map<String, Anchor> createAnchors() {
    Map<String, Anchor> anchors = new HashMap<>();
    Anchor anchor1 = Anchor.builder().id("anchor_1").build();
    anchors.put(anchor1.getId(), anchor1);
    return anchors;
  }
}

# OntoUML4J

OntoUML4J is a Java library for working with OntoUML models. This library provides a set of classes and methods to create, manipulate, and analyze OntoUML models programmatically.

<!-- ## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Classes](#classes)
    - [Class1](#class1)
    - [Class2](#class2)
    - [Class3](#class3)
- [Contributing](#contributing)
- [License](#license) -->

## Installation

To include OntoUML4J in your project, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.ontouml</groupId>
    <artifactId>ontouml4j</artifactId>
    <version>0.1.0</version>
</dependency>
```

## Usage

Here is an example of how to use OntoUML4J in your project:

```java
import org.ontouml.model.MultilingualText;
import org.ontouml.model.Project;

public class Main {
  public static void main(String[] args) {
    Project myProject =
        Project.builder()
            .id("12345")
            .name(new MultilingualText("Project Name"))
            .description(new MultilingualText("Project Description"))
            .build();

    System.out.println(myProject.getName());
    System.out.println(myProject.getId());
  }
}

```

## Contributing

Contributions are welcome! Please read the [contributing guidelines](CONTRIBUTING.md) for more information.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
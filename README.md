# tool-openapi-generator

## Description
This project allow to generate server-side project from [OpenAPI specification](https://github.com/OAI/OpenAPI-Specification).
The reference project for generated files [jepria-showcase](https://github.com/Jepria/jepria-showcase/tree/master/module/JepRiaShowcase).

## Usage
### Binary files
Binary files placed in [bin-repo](https://github.com/Jepria/bin-repo).
Current versions: 
  [generator-cli-1.0.jar](https://github.com/Jepria/bin-repo/blob/master/build/org/jepria/tools/openapi/generator-cli/0.0.1/generator-cli-0.0.1.jar)
  [generator-1.0.jar](https://github.com/Jepria/bin-repo/blob/master/build/org/jepria/tools/openapi/generator/0.0.1/generator-0.0.1.jar)
### Command line options
```
-i         - input specification file
-o         - output directory
-pkg       - main package
-g         - generation options
   tests   - generate rest adapters 
   proj    - generate project
```

#### Example use of generator-cli.jar from bin repository.
##### Generate project
```
java -jar %BIN_HOME%/build/org/jepria/tools/openapi/generator-cli/0.0.1/generator-cli-0.0.1.jar -i samples/swagger.json -o samples/feature/project/ -g proj -pkg org.jepria.showcase
```
##### Generate tests
```
java -jar %BIN_HOME%/build/org/jepria/tools/openapi/generator-cli/0.0.1/generator-cli-0.0.1.jar -i samples/swagger.json -o samples/feature/test/ -g tests -pkg org.jepria.showcase
```
## Build projects
To build from source, you need the following installed and available in your $PATH:
* [Java 8](https://www.oracle.com/technetwork/java/index.html)
* [Apache Maven ](https://maven.apache.org/)

After cloning the project, you can build it from source with this command:
```sh
mvn clean install
```
## Customizing
To change output file you can modify [mustache template files](https://github.com/Jepria/tool-openapi-generator/tree/master/modules/tool-openapi-generator/src/main/resources/mustache-templates).

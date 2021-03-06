package org.jepria.tools.openapi.generator.languages.jersey;

import static org.jepria.tools.openapi.generator.languages.jersey.Templates.CRUD_TEST_TEMPLATE;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jepria.tools.openapi.generator.GeneratorImpl;
import org.jepria.tools.openapi.generator.languages.jersey.models.entity.rest.JaxrsAdapterModel;
import org.jepria.tools.openapi.generator.languages.jersey.models.test.JaxrsCrudTestModel;

public class CrudTestsCreator {

  private String outputFolder;
  private String basePackage = "";

  public CrudTestsCreator(String outputFolderName) {
    this.outputFolder = outputFolderName;
  }

  public void create(String specFileLocation) throws IOException {
    OpenAPI openAPI = null;
    try {
      openAPI = new OpenAPIV3Parser().read(specFileLocation);
    } catch (Exception e) {
      System.err.println("Cannot read spec from file!");
      return;
    }
    create(openAPI);
  }

  public void create(OpenAPI spec) throws IOException {
    if (null == spec) {
      throw new IllegalArgumentException("openAPI argument cannot be null");
    }

    File file = new File(outputFolder);

    Boolean check = file.mkdir();

    String testFolder = this.outputFolder + "\\src\\test\\java\\" + this.basePackage.replace(".", "\\") + "\\";

    List<JaxrsAdapterModel> models = JaxrsAdapterModel.getFromSpec(spec, this.basePackage);
    for (JaxrsAdapterModel model : models) {
      String className     = model.getClassName();
      String entityPackage = this.basePackage + "." + className.toLowerCase();

      JaxrsCrudTestModel dto = new JaxrsCrudTestModel();
      dto.setApiPackage(entityPackage);
      dto.setClassName(className);
      dto.setModelPackage(entityPackage + ".dto");

      String fileName = className + "JaxrsAdapterCrudTestIT.java";

      GeneratorImpl generator = new GeneratorImpl();

      generator.generate(dto, testFolder + className.toLowerCase() + "\\", fileName, CRUD_TEST_TEMPLATE);
    }
  }

  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }
}

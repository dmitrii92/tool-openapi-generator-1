package org.jepria.tools.openapi.generator.languages.jersey.generators;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jepria.tools.openapi.generator.languages.jersey.generators.entity.dto.DtoGenerator;
import org.junit.jupiter.api.Test;

class DtoGeneratorTest {

  @Test
  void createTest() throws IOException {
    String specLocation = new File(getClass().getClassLoader().getResource("spec/swagger.json").getPath()).getCanonicalPath();
    String outputFolder = new java.io.File(".").getCanonicalPath() + "\\temp\\DtoGeneratorTest\\";

    if (new File(outputFolder).exists()) {
      FileUtils.cleanDirectory(new File(outputFolder));
    }

    DtoGenerator generator = new DtoGenerator(specLocation);
    generator.create();
    generator.saveToFiles(outputFolder);

  }

}
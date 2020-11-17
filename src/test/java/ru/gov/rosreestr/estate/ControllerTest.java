package ru.gov.rosreestr.estate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import ru.gov.rosreestr.estate.model.dto.Response;
import ru.gov.rosreestr.estate.service.UploadService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = LoadLandPlotsApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private UploadService uploadService;

  @Value("classpath:doc15799654.xml")
  private Resource exampleXml;

  @Test
  public void ControllerHandleFileUploadTest() throws Exception {
    String fileName = "fileName";
    MultipartFile fileContent = new MockMultipartFile("file",
        fileName, "application/xml", new FileInputStream(exampleXml.getFile()));

    when(uploadService.handleFileUpload(fileName, fileContent))
        .thenReturn(new Response("Вы успешно загрузили файл: " + fileName + " на сервер.. "));

    MockMultipartFile file
        = new MockMultipartFile(
        "file",
        "test.xml",
        String.valueOf(MediaType.TEXT_XML),
        fileContent.getBytes()
    );

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
        .file(file)
        .param("name","fileName"))
        .andExpect(status().isOk());
  }
}

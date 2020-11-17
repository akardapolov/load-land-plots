package ru.gov.rosreestr.estate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import ru.gov.rosreestr.estate.model.dto.Response;
import ru.gov.rosreestr.estate.model.entity.ParcelsTable;
import ru.gov.rosreestr.estate.model.repository.ParcelsTableRepository;
import ru.gov.rosreestr.estate.service.UploadService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = LoadLandPlotsApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ServiceTest {
  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private UploadService uploadService;

  @Autowired
  private Unmarshaller unmarshaller;

  @MockBean
  private ParcelsTableRepository parcelsTableRepository;

  @Value("classpath:doc15799654.xml")
  private Resource exampleXml;

  @Test
  public void ControllerHandleFileUploadTest() throws Exception {
    String fileName = "fileName";
    MultipartFile fileContent = new MockMultipartFile("file",
        fileName, "application/xml", new FileInputStream(exampleXml.getFile()));

    Response expected = new Response("Вы успешно загрузили файл: " + fileName + " на сервер.. ");

    when(parcelsTableRepository.save(Mockito.any(ParcelsTable.class))).thenReturn(new ParcelsTable());

    MockMultipartFile file
        = new MockMultipartFile(
        "file",
        "test.xml",
        String.valueOf(MediaType.TEXT_XML),
        fileContent.getBytes()
    );

    Response actual = uploadService.handleFileUpload(fileName, file);

    assertEquals(expected.getMessage(), actual.getMessage());
  }
}

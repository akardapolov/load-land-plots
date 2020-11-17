package ru.gov.rosreestr.estate;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gov.rosreestr.estate.model.repository.ParcelsTableRepository;
import ru.gov.rosreestr.estate.service.UploadService;
import ru.rosreestr.artefacts.x.outgoing.kpt._10_0.KPT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoadLandPlotsApplication.class)
@ActiveProfiles("test")
public class UnmarshallTest {
  String expected = "90:15:050502";

  @Value("classpath:doc15799654.xml")
  private Resource exampleXml;

  @MockBean
  private UploadService uploadService;

  @MockBean
  private ParcelsTableRepository parcelsTableRepository;

  @Test
  public void unmarshallerKptTest() throws Exception {
    JAXBContext jc = JAXBContext.newInstance(KPT.class);

    Unmarshaller unmarshaller = jc.createUnmarshaller();
    KPT kpt = (KPT) unmarshaller.unmarshal(exampleXml.getFile());

    String actual =
        kpt.getCadastralBlocks().getCadastralBlock()
            .stream().findFirst().get().getCadastralNumber();

    kpt.getCadastralBlocks().getCadastralBlock()
        .stream().forEach(k -> {
      k.getParcels().getParcel().forEach(l->{
        System.out.println(l.getName());
      });
    });

    assertEquals(expected, actual);
  }

}

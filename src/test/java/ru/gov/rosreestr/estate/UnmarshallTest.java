package ru.gov.rosreestr.estate;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.rosreestr.artefacts.x.outgoing.kpt._10_0.KPT;

/**
 * todo fix it -> БД должна быть доступна для прохождения теста
 * см. application-local.properties
 * spring.datasource.url=jdbc:postgresql://localhost:5433/load_land_plots
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoadLandPlotsApplication.class)
@ActiveProfiles("local")
public class UnmarshallTest {
  String expected = "90:15:050502";

  @Value("classpath:doc15799654.xml")
  private Resource exampleXml;

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

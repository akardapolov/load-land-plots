package ru.gov.rosreestr.estate.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rosreestr.artefacts.x.outgoing.kpt._10_0.KPT;

@Configuration
public class CommonConfig {
  @Bean
  public Unmarshaller setUnmarshaller() throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance(KPT.class);
    return jc.createUnmarshaller();
  }
}

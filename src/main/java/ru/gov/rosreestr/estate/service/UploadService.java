package ru.gov.rosreestr.estate.service;

import java.io.ByteArrayInputStream;
import javax.transaction.Transactional;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.gov.rosreestr.estate.model.dto.Response;
import ru.gov.rosreestr.estate.model.entity.ParcelsTable;
import ru.gov.rosreestr.estate.model.repository.ParcelsTableRepository;
import ru.gov.rosreestr.estate.utility.exception.ServiceUploadException;
import ru.rosreestr.artefacts.x.outgoing.kpt._10_0.KPT;

@Service
public class UploadService {
  private ParcelsTableRepository parcelsTableRepository;
  private Unmarshaller unmarshaller;

  public UploadService(ParcelsTableRepository parcelsTableRepository,
      Unmarshaller unmarshaller) {
    this.parcelsTableRepository = parcelsTableRepository;
    this.unmarshaller = unmarshaller;
  }

  @Transactional
  public Response handleFileUpload(String fileName, MultipartFile fileContent)
      throws ServiceUploadException {

    if (fileName == null || fileName.isEmpty()) {
      throw new ServiceUploadException("Не указано имя файла");
    }

    if (!fileContent.isEmpty()) {
      try {
        KPT kpt = (KPT) unmarshaller.unmarshal(new ByteArrayInputStream(fileContent.getBytes()));

        kpt.getCadastralBlocks().getCadastralBlock().forEach(e ->
            e.getParcels().getParcel().forEach(k ->
                parcelsTableRepository.save(ParcelsTable.builder()
                    .name(k.getName())
                    .category(k.getCategory())
                    .utilization(k.getUtilization().getByDoc())
                    .build())));

        return new Response("Вы успешно загрузили файл: " + fileName + " на сервер.. ");
      } catch (Exception e) {
        throw new ServiceUploadException(
            "Не удалось загрузить файл: " + fileName + " => " + e.getMessage());
      }
    } else {
      throw new ServiceUploadException(
          "Не удалось загрузить пустой файл: " + fileName);
    }

  }
}

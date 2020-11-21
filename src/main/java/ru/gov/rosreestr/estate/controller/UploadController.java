package ru.gov.rosreestr.estate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.gov.rosreestr.estate.model.dto.Response;
import ru.gov.rosreestr.estate.service.UploadService;
import ru.gov.rosreestr.estate.utility.annotation.CustomExceptionHandler;
import ru.gov.rosreestr.estate.utility.exception.ServiceUploadException;

@RestController
@CustomExceptionHandler
public class UploadController {
  private UploadService uploadService;

  public UploadController(UploadService uploadService) {
    this.uploadService = uploadService;
  }

  @PostMapping(value="/upload")
  public ResponseEntity<Response> handleFileUpload(@RequestParam("name") String fileName,
      @RequestParam("file") MultipartFile fileContent) throws ServiceUploadException {
   return ResponseEntity.ok(uploadService.handleFileUpload(fileName, fileContent));
  }

}

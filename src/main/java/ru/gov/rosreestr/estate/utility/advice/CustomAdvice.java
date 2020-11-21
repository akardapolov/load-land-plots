package ru.gov.rosreestr.estate.utility.advice;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gov.rosreestr.estate.model.dto.Response;
import ru.gov.rosreestr.estate.utility.annotation.CustomExceptionHandler;

@ControllerAdvice(annotations = CustomExceptionHandler.class)
public class CustomAdvice {

  @ExceptionHandler(ru.gov.rosreestr.estate.utility.exception.ServiceUploadException.class)
  public ResponseEntity<Response> handleException(
      ru.gov.rosreestr.estate.utility.exception.ServiceUploadException e) {
    String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
    Response response = new Response(message);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}

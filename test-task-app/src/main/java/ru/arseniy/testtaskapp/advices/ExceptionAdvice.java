package ru.arseniy.testtaskapp.advices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.arseniy.testtaskapp.dto.BaseResponse;
import ru.arseniy.testtaskapp.exceptions.CustomerNotFoundException;
import ru.arseniy.testtaskapp.exceptions.RequestValidationException;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({RequestValidationException.class, CustomerNotFoundException.class})
    public ResponseEntity<BaseResponse> handleRequestValidationException(Exception e) {
        BaseResponse response = BaseResponse.builder()
                .Success(false)
                .Reason(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleException(Exception e) {
        e.printStackTrace();
        BaseResponse response = BaseResponse.builder()
                .Success(false)
                .Reason("Упс, что-то пошло не так, попробуйте повторить запрос позднее.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

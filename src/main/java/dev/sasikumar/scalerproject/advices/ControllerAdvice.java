package dev.sasikumar.scalerproject.advices;

import dev.sasikumar.scalerproject.DTOs.ErrorDTO;
import dev.sasikumar.scalerproject.exceptions.NotValidCategoryException;
import dev.sasikumar.scalerproject.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException exception){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidCategoryException.class)
    public ResponseEntity<ErrorDTO> handleNotValidCategoryException(NotValidCategoryException exception){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}

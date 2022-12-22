package br.com.banco.exception;

import br.com.banco.exception.messages.ContaNaoEncontradaException;
import br.com.banco.exception.messages.TransferenciaNaoEncontradaException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler({
            InvalidFormatException.class,
            PropertyValueException.class,
            MysqlDataTruncation.class
    })
    public ResponseEntity<Object> handlerBadRequest(Exception exception) {
        return new ResponseEntity<>(new DataError(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ContaNaoEncontradaException.class,
            TransferenciaNaoEncontradaException.class
    })
    public ResponseEntity<Object> handlerNotFound(Exception exception) {
        return new ResponseEntity<>(new DataError(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}

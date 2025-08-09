package com.todostudy.config;

import com.todostudy.cmn.ObjResVO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ObjResVO<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", 이건 다음검증! -> "));

        //objres에다가 담아서 유저한테 떤지기
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ObjResVO.<String>builder().message(errorMessage).build());
    }


    //db형식같은에러처리
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ObjResVO<String>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String databaseErrorMessage = e.getRootCause().getMessage();

        if (databaseErrorMessage != null && databaseErrorMessage.contains("Incorrect datetime value")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ObjResVO.<String>builder().message("날짜형식이 이상함").build());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ObjResVO.<String>builder().message("저장못하는값임!").build());
    }
}

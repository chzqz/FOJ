package icu.chzqz.foj.handler;

import icu.chzqz.foj.entity.exception.AccessDeniedException;
import icu.chzqz.foj.entity.exception.NotFoundException;
import icu.chzqz.foj.entity.exception.RequestFailException;
import icu.chzqz.foj.entity.exception.SystemErrorException;
import icu.chzqz.foj.properties.MessageProperty;
import icu.chzqz.foj.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    MessageProperty messageProperty;

    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedException(AccessDeniedException e){
        log.info("AccessDeniedException: {}", e.toString());
        return Result.error(402,e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public Result notFoundException(NotFoundException e){
        log.info("NotFoundException: {}", e.toString());
        return Result.error(404,e.getMessage());
    }
    @ExceptionHandler(RequestFailException.class)
    public Result requestFailException(RequestFailException e){
        log.info("RequestFailException: {}", e.toString());
        return Result.error(400,e.getMessage());
    }
    @ExceptionHandler(SystemErrorException.class)
    public Result systemErrorException(SystemErrorException e){
        log.info("AccessDeniedException: {}", e.toString());
        return Result.error(500,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        log.info("Exception: {}",e.toString());
        e.printStackTrace();
        return Result.error(520,e.getMessage());
    }
}

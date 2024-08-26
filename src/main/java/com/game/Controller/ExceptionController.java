package com.game.Controller;

import com.game.Constant.Code;
import com.game.Controller.Response.Result;
import com.game.CusException.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Result loginExpHandler(LoginException e) {
        log.info(e.getMessage());
        return new Result(e.getStatus() ,e.getMessage());
    }

}

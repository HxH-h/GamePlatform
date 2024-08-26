package com.game.Controller;

import com.game.Constant.Code;
import com.game.Constant.Message;
import com.game.Controller.ControllerPojo.LoginDTO;
import com.game.Controller.Response.Result;
import com.game.CusException.LoginException;
import com.game.Service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "玩家登录")
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerServiceImpl;

    @PostMapping("/login")
    @Operation(summary = "登录接口")
    public Result login(@RequestBody LoginDTO loginDTO) throws LoginException {
        String jwt = playerServiceImpl.login(loginDTO);
        return new Result(Code.LOGIN_SUCCESS, Message.LOGIN_SUCCESS, jwt);
    }



}

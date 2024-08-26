package com.game.Controller;

import com.game.Constant.Code;
import com.game.Constant.Message;
import com.game.Controller.ControllerPojo.LoginDTO;
import com.game.Controller.ControllerPojo.RegisterDTO;
import com.game.Controller.Response.Result;
import com.game.CusException.LoginException;
import com.game.CusException.RegisterException;
import com.game.Service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    @Operation(summary = "注册接口")
    public Result register(@RequestBody RegisterDTO registerDTO) throws RegisterException {
        playerServiceImpl.register(registerDTO);
        return new Result(Code.REGISTER_SUCCESS, Message.REGISTER_SUCCESS);
    }

    // TODO 获取浏览器信息 防止被无限获取验证码
    @GetMapping("/genCode/{email}")
    @Operation(summary = "生成验证码")
    public Result genCode(@PathVariable String email){
        playerServiceImpl.getCode(email);
        return new Result<>(Code.GETCODE_SUCCESS, Message.GETCODE_SUCCESS);
    }



}

package com.game.Service;

import com.game.Controller.ControllerPojo.LoginDTO;
import com.game.Controller.ControllerPojo.RegisterDTO;
import com.game.CusException.LoginException;
import com.game.CusException.RegisterException;
import org.springframework.stereotype.Service;


@Service
public interface PlayerService {

    String login(LoginDTO loginDTO) throws LoginException ;
    void register(RegisterDTO registerDTO) throws RegisterException;
    void getCode(String email);

}

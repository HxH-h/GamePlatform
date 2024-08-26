package com.game.Service;

import com.game.Controller.ControllerPojo.LoginDTO;
import com.game.CusException.LoginException;
import org.springframework.stereotype.Service;


@Service
public interface PlayerService {

    String login(LoginDTO loginDTO) throws LoginException ;
}

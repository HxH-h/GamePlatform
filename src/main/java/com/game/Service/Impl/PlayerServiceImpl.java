package com.game.Service.Impl;

import com.game.Constant.Code;
import com.game.Constant.Message;
import com.game.Controller.ControllerPojo.LoginDTO;
import com.game.CusException.LoginException;
import com.game.Dao.Mapper.PlayerMapper;
import com.game.Service.PlayerService;
import com.game.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerMapper playerMapper;

    @Override
    public String login(LoginDTO loginDTO) throws LoginException {
        String regex =  "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

        String pwd = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        String uuid = null;
        if (loginDTO.getUsername().matches(regex)){
            uuid = playerMapper.selectByEmail(loginDTO.getUsername(), pwd);
        }else {
            uuid = playerMapper.selectByName(loginDTO.getUsername(), pwd);
        }

        if (uuid != null){
            return JWTUtils.getJWT(uuid);
        }else {
            throw new LoginException(Code.LOGIN_FAILURE, Message.LOGIN_FAILURE);
        }

    }
}

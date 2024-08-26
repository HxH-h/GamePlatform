package com.game.Service.Impl;

import com.game.Constant.Code;
import com.game.Constant.Message;
import com.game.Constant.RedisConstant;
import com.game.Controller.ControllerPojo.LoginDTO;
import com.game.Controller.ControllerPojo.RegisterDTO;
import com.game.CusException.LoginException;
import com.game.CusException.RegisterException;
import com.game.Dao.Mapper.PlayerMapper;
import com.game.Dao.Pojo.Player;
import com.game.Service.PlayerService;
import com.game.Service.ServicePojo.EmailVO;
import com.game.Utils.EmailUtils;
import com.game.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerMapper playerMapper;

    @Value("${indentity.code}")
    String identifycode;

    @Value("${indentity.expire}")
    Integer expire;

    @Autowired
    RedisTemplate redisTemplate;

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

    @Override
    public void register(RegisterDTO registerDTO) throws RegisterException {
        //校对验证码
        String code = (String)redisTemplate.opsForValue().get(RedisConstant.IndentityCode + registerDTO.getEmail());
        if (registerDTO.getCode().equals(code)){

            redisTemplate.delete(RedisConstant.IndentityCode + registerDTO.getEmail());

            //校对邮箱格式和用户名格式

            if (registerDTO.getEmail().matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")&&
                registerDTO.getUsername().matches("^[A-Za-z][A-Za-z0-9!#$%^&*]*$")){
                //验证用户名和邮箱是否重复
                String []uuid = playerMapper.playerExsit(registerDTO.getUsername(), registerDTO.getEmail());
                if (uuid == null || uuid.length == 0){
                    //密码加密
                    String pwd = DigestUtils.md5DigestAsHex(registerDTO.getPassword().getBytes());
                    //插入数据库
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    String format = formatter.format(date);

                    Player player = Player.builder()
                                          .uuid(UUID.randomUUID().toString().replaceAll("-",""))
                                          .username(registerDTO.getUsername())
                                          .password(pwd)
                                          .email(registerDTO.getEmail())
                                          .addtime(format)
                                          .level(0)
                                          .isProhibit(0)
                                          .photo(null)
                                          .build();
                    playerMapper.addPlayer(player);

                }else {
                    throw new RegisterException(Code.PLAYER_EXIST,Message.PLAYER_EXIST);
                }
            }else {
                throw new RegisterException(Code.FORMAT_ERROR,Message.FORMAT_ERROR);
            }
        }else {
            throw new RegisterException(Code.CODE_ERROR,Message.CODE_ERROR);
        }

    }

    @Override
    public void getCode(String email) {
        String code = "";
        Random rand = new Random();
        for (int i = 0; i < 6; i++){
            code += identifycode.charAt(rand.nextInt(identifycode.length()));
        }

        redisTemplate.opsForValue().set(RedisConstant.IndentityCode + email, code,expire, TimeUnit.MINUTES);
        EmailUtils.sendEmail(new EmailVO(new String[]{email}, "验证码", "您的验证码是：" + code));

    }
}

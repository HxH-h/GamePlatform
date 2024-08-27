package com.game.Controller.Interceptor;

import com.game.Constant.Code;
import com.game.Constant.Message;
import com.game.Controller.UserInfoThread;
import com.game.CusException.LoginException;
import com.game.Utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("Authorization");

        if (token == null) {
            System.out.println("拦截请求" + request.getRequestURI());
            throw new LoginException(Code.NEEDLOGIN,Message.NEEDLOGIN);
        }

        try {

            String uuid = JWTUtils.parseJWT(token, "uuid");
            UserInfoThread.setInfo(uuid);

        } catch (Exception e) {
            System.out.println("token错误 "+"拦截请求" + request.getRequestURI());
            throw new LoginException(Code.NEEDLOGIN,Message.NEEDLOGIN);
        }

        return true;
    }
}

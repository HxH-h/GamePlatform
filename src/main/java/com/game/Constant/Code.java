package com.game.Constant;

// 消息属性  消息子子类别  消息子类别 消息类别
//    X         X          X        X
//   成功
//   失败
//   中性

public class Code {

    // 用户消息类别
        //成功
    public static final Integer LOGIN_SUCCESS = 1001;
    public static final Integer REGISTER_SUCCESS = 1011;
    public static final Integer GETCODE_SUCCESS = 1021;
    public static final Integer GETINFO_SUCCESS = 1031;
        //失败
    public static final Integer LOGIN_FAILURE = 2001;
    public static final Integer CODE_ERROR = 2011;
    public static final Integer FORMAT_ERROR = 2021;
    public static final Integer GETCODE_FAILURE = 2031;
        // 中性
    public static final Integer NEEDLOGIN = 3001;
    public static final Integer PLAYER_EXIST = 3011;



}

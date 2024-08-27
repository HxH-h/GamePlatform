package com.game.Dao.Pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Player {
    String uuid;
    String username;
    String password;
    String email;
    String addtime;
    int level;
    int score;
    int isProhibit;
    String photo;
}

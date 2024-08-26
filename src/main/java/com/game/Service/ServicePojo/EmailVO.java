package com.game.Service.ServicePojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailVO {
    private String []to;
    private String subject;
    private String content;
}

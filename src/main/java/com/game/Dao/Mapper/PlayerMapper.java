package com.game.Dao.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PlayerMapper {

    @Select("select uuid from player where username = #{username} and password = #{password}")
    String selectByName(String username , String password);

    @Select("select uuid from player where email = #{email} and password = #{password}")
    String selectByEmail(String email, String password);

}

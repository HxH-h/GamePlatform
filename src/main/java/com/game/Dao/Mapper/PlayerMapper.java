package com.game.Dao.Mapper;

import com.game.Dao.Pojo.Player;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PlayerMapper {

    @Select("select uuid from player where username = #{username} and password = #{password}")
    String selectByName(String username , String password);

    @Select("select uuid from player where email = #{email} and password = #{password}")
    String selectByEmail(String email, String password);

    @Select("select uuid from player where username = #{username} or email = #{email}")
    String []playerExsit(String username, String email);

    @Insert("insert into player(uuid, username, password, email, photo, addtime, isProhibit, level) values(#{uuid}, #{username}, #{password}, #{email}, #{photo}, #{addtime}, #{isProhibit}, #{level})")
    void addPlayer(Player player);
}

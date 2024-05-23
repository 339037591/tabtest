package com.aas.dao.SqlProvider;

import org.apache.ibatis.annotations.Param;

public class SysUserSqlProvider {
    public String getSysUserByParameter(@Param("id")String id){
        return "select * from sys_user where id = #{id}";
    }

}

package com.aas.dao;

import com.aas.dao.SqlProvider.SysUserSqlProvider;
import com.aas.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserDao  {

    @SelectProvider(type = SysUserSqlProvider.class, method = "getSysUserByParameter")
    List<SysUser> getSysUserByParameter(@Param("id") String id);

    @SelectProvider(type = SysUserSqlProvider.class, method = "getSysUserByParameter")
    List<Map> getSysUserBytest(@Param("id") String id);
}

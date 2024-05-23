package com.aas.service;

import com.aas.dao.SysUserDao;
import com.aas.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public SysUserDao getSysUserDao(){
        return this.sysUserDao;
    }

    public List<SysUser> getSysUserByParameter(String id){
        return sysUserDao.getSysUserByParameter(id);
    }
}

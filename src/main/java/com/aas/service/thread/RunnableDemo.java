package com.aas.service.thread;

import com.aas.dao.SysUserDao;
import com.aas.entity.SysUser;

public class RunnableDemo extends Thread{
    private Thread t;
    private String threadName;// 线程名字
    private SysUserDao sysUserDao;
    private SysUser sysUser;

    public RunnableDemo(String name, SysUserDao sysUserDao) {
        this.threadName = name;
        this.sysUserDao = sysUserDao;
    }
    @Override
    public void run() {

        try {

            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SysUser getSysUser(){
        return sysUser;
    }
}

package com.aas.schedule;

import com.aas.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(prefix = "scheduling",name = "enabled",havingValue = "true")
public class ScheduleService {

    @Autowired
    SysUserService sysUserService;

    @Scheduled(cron = "*/5 * * * * ?")
    public void demoSchedule(){
        List list = sysUserService.getSysUserByParameter("admin");
        list.forEach(e-> {
            System.out.println(e.toString());
        });
    }
}

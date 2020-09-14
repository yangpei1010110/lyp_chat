package com.lyp.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupViewController {

    /**
     * 查询活跃度最高的群 暂时使用随机代替
     */
    @PostMapping("/hot")
    @RequiresRoles("visitor,admin,manager,root")//游客，用户，管理员，root
    public Map<String, Object> getHotGroup() {


        return null;
    }
}

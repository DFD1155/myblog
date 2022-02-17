package com.dfd.vueblog_java.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dfd.vueblog_java.entity.MUser;
import com.dfd.vueblog_java.service.MUserService;
import com.dfd.vueblog_java.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Deng
 * @since 2022-02-13
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息查询")
public class MUserController {
    @Autowired
    private MUserService mUserService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R login(@RequestBody String user, HttpServletRequest request) {
        MUser mUser = new MUser();
        Map map = (Map) JSON.parse(user);
        QueryWrapper<MUser> qw = new QueryWrapper<>();
        if (map != null) {
            mUser.setUsername((String) map.get("username"));
            mUser.setPassword((String) map.get("password"));
        }
        qw.eq("username", mUser.getUsername());
        qw.eq("password", mUser.getPassword());
        MUser userList = mUserService.getOne(qw, false);
        HttpSession session = request.getSession();
        if (userList != null) {
            userList.setPassword(null);
            session.setAttribute("token", userList.getId());
            System.out.println(userList.getId() + "-------------------" + session.getAttribute("token") + "----------------");
            return R.ok().data("token", session.getId()).data("userInfo", userList).message("登录成功！");
        } else {
            return R.error().data("token", null).message("登录失败！");
        }
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUser")
    public R updateUser(MUser user) {
        UpdateWrapper<MUser> ow = new UpdateWrapper<>();
        ow.eq("username", "Deng");
        user.setCreated(new Date());
        boolean updateFlag = mUserService.update(user, ow);
        if (updateFlag) {
            return R.ok().message("修改成功！");
        } else {
            return R.error().message("修改失败！");
        }
    }

    @ApiOperation("登出接口")
    @GetMapping("/logout")
    public R logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return R.ok().message("logout success!");
    }
}


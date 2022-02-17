package com.dfd.vueblog_java.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfd.vueblog_java.entity.MBlog;
import com.dfd.vueblog_java.entity.MUser;
import com.dfd.vueblog_java.service.MBlogService;
import com.dfd.vueblog_java.service.MUserService;
import com.dfd.vueblog_java.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
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
@RequestMapping("/blog")
@Api(tags = "博客信息查询")
public class MBlogController {
    @Autowired
    private MBlogService mBlogService;

    @ApiOperation("分页查询博客信息")
    @GetMapping("/query")
    public R queryPage(Integer current, Integer size) {
        QueryWrapper<MBlog> qw = new QueryWrapper<>();
        qw.orderByDesc("created");
        Page<MBlog> blogPage = new Page<>(current, size);
        mBlogService.page(blogPage, qw);
        List<MBlog> records = blogPage.getRecords();
        return R.ok().data("records", records)
                .data("current", blogPage.getCurrent())
                .data("total", blogPage.getTotal())
                .data("size", blogPage.getSize()).message(null);
    }

    @ApiOperation("查询博客详情信息")
    @GetMapping("/query/{blogId}")
    public R queryPage(@PathVariable Integer blogId) {
        QueryWrapper<MBlog> qw = new QueryWrapper<>();
        qw.eq("id", blogId);
        MBlog blog = mBlogService.getOne(qw, false);
        if (blog != null) {
            return R.ok().data("blog", blog).message(null);
        } else {
            return R.error().message("内容不存在");
        }
    }

    @ApiOperation("修改博客")
    @PostMapping("/edit")
    public R editBlog(@RequestBody String ruleForm) {
        Map map = (Map) JSON.parse(ruleForm);
        MBlog blog = new MBlog();

        Long blogId_long = null;
        if (map != null) {
            if (!(map.get("id") instanceof String)) {
                Integer blog_int = (Integer) map.get("id");
                blogId_long = blog_int.longValue();
            }
            blog.setTitle((String) map.get("title"));
            blog.setContent((String) map.get("content"));
            blog.setDescription((String) map.get("description"));
            blog.setId(blogId_long);
        }
        boolean editFlag = false;

        QueryWrapper<MBlog> qw = new QueryWrapper<>();
        qw.eq("id", blogId_long);
        MBlog byId = mBlogService.getOne(qw);
        if (byId != null) {
            editFlag = mBlogService.updateById(blog);
        } else {
            long userId = 1;
            blog.setUserId(userId);
            blog.setCreated(new Date());
            editFlag = mBlogService.save(blog);
        }


        if (editFlag) {
            return R.ok().message("操作成功！");
        } else {
            return R.error().message("操作失败！");
        }
    }

    @ApiOperation("删除博客")
    @DeleteMapping("/deleteBlog/{id}")
    public R deleteBlog(@PathVariable Long id) {
        boolean removeFlag = mBlogService.removeById(id);
        if (removeFlag) {
            return R.ok().message("删除成功！");
        } else {
            return R.error().message("删除失败！");
        }
    }
}


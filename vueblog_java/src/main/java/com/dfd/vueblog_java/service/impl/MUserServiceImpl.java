package com.dfd.vueblog_java.service.impl;

import com.dfd.vueblog_java.entity.MUser;
import com.dfd.vueblog_java.mapper.MUserMapper;
import com.dfd.vueblog_java.service.MUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Deng
 * @since 2022-02-13
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

}

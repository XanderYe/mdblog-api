package com.xander.mdblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.xander.mdblog.base.ResultBean;
import com.xander.mdblog.config.RedisService;
import com.xander.mdblog.constant.Constants;
import com.xander.mdblog.util.ShortUUIDUtil;
import com.xander.mdblog.util.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 叶振东
 * @date 2019/8/27
 */
@Api(value = "验证码controller", tags = {"验证码操作接口"})
@RestController
public class CaptchaController {
    @Autowired
    RedisService redisService;

    /**
     * 验证码
     *
     * @return com.xander.mdblog.base.ResultBean
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value = "获取验证码", notes = "不需要登录;返回uuid和base64码")
    @GetMapping("captcha")
    public ResultBean captcha() {
        JSONObject captcha = new JSONObject();
        String uuid = ShortUUIDUtil.getShortUUID();
        String code = VerifyCodeUtils.generateVerifyCode(4);
        String image = VerifyCodeUtils.imageBase64(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, code);
        // 存入redis
        redisService.set(uuid, code, Constants.REDIS_CAPTCHA_EXPIRE);

        captcha.put("uuid", uuid);
        captcha.put("image", image);
        return new ResultBean<>(captcha);
    }
}

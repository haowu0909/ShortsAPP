package com.wuhao.controller;

import com.wuhao.base.BaseInfoProperties;
import com.wuhao.bo.RegistLoginBO;
import com.wuhao.grace.result.GraceJSONResult;
import com.wuhao.grace.result.ResponseStatusEnum;
import com.wuhao.pojo.Users;
import com.wuhao.service.UserService;
import com.wuhao.utils.IPUtil;
import com.wuhao.utils.SMSUtils;
import com.wuhao.vo.UsersVO;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
让用户在输入手机号界面输入手机号，获得验证码的模块
 */
@Slf4j
@Api(tags = "PassportController 通行证接口模块")
@RequestMapping("passport")
@RestController
public class PassportController extends BaseInfoProperties {

  @Autowired
  private SMSUtils smsUtils;

  @Autowired
  private UserService userService;


  @PostMapping("getSMSCode")
  public GraceJSONResult getSMSCode(@RequestParam String mobile, HttpServletRequest request ) throws Exception {
    if(StringUtils.isBlank(mobile)){
      return GraceJSONResult.ok();
    }

    // get ip of user, limits user get verification only every 60 sec
    String userIp = IPUtil.getRequestIp(request);

//    redis.setnx60s(MOBILE_SMSCODE +": " + userIp, userIp);

//    String code = (int)((Math.random() * 9 + 1) * 100000) + "";   //模版变量只能传入0 - 6位（包括6位）纯数字
    String code = "123456";
    smsUtils.sendSMS(mobile,code);

    log.info(code);

//    redis.set(MOBILE_SMSCODE + ":" + mobile, code, 30 * 60);  //30 min 有效期
//    log.info("code has been write:" + redis.get(MOBILE_SMSCODE + ":" + mobile));

    //put verification code in redis, for following verification
    return GraceJSONResult.ok();
  }

  @PostMapping("login")
  public GraceJSONResult login(@Valid @RequestBody RegistLoginBO registLoginBO,
//                               BindingResult result, 对代码有侵入性
                               HttpServletRequest request ) throws Exception{
    // 0. 判断BindingResult中是否保存了错误的验证信息，如果有，则需要返回到前端
//        if( result.hasErrors() ) {
//            Map<String, String> map = getErrors(result);
//            return GraceJSONResult.errorMap(map);
//        }

    String mobile = registLoginBO.getMobile();
    String code = registLoginBO.getSmsCode();

    // 1. 从redis中获得验证码进行校验是否匹配

//    String redisCode = redis.get(MOBILE_SMSCODE + ":" + mobile);
    log.info("code: " + code);
//    log.info("redis" + redisCode);
//    if (StringUtils.isBlank(redisCode) || !redisCode.equalsIgnoreCase(code)) {
//
//      return GraceJSONResult.errorCustom(ResponseStatusEnum.SMS_CODE_ERROR);
//    }

    // 2. 查询数据库，判断用户是否存在
    Users user = userService.queryMobileIsExist(mobile);
    if (user == null) {
      // 2.1 如果用户为空，表示没有注册过，则为null，需要注册信息入库
      user = userService.createUser(mobile);
    }
//    log.info("the user has been set: ");
    // 3. 如果不为空，可以继续下方业务，可以保存用户会话信息 分布式
    String uToken = UUID.randomUUID().toString();

//    redis.set(REDIS_USER_TOKEN + ":" + user.getId(), uToken);

    // 4. 用户登录注册成功以后，删除redis中的短信验证码
//    redis.del(MOBILE_SMSCODE + ":" + mobile);

    // 5. 返回用户信息，包含token令牌
    UsersVO usersVO = new UsersVO();
    BeanUtils.copyProperties(user, usersVO);
    usersVO.setUserToken(uToken);

    return GraceJSONResult.ok(usersVO);
//    return GraceJSONResult.ok();
  }

  @PostMapping("logout")
  public GraceJSONResult logout(@RequestParam String userId,
                                HttpServletRequest request) throws Exception {

    // 后端只需要清除用户的token信息即可，前端也需要清除，清除本地app中的用户信息和token会话信息
//    redis.del(REDIS_USER_TOKEN + ":" + userId);

    return GraceJSONResult.ok();
  }
}

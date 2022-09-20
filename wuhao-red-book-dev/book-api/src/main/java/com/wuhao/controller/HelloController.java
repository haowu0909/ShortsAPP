package com.wuhao.controller;

import com.wuhao.grace.result.GraceJSONResult;
import com.wuhao.grace.result.ResponseStatusEnum;
import com.wuhao.utils.SMSUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "Hello 测试的接口")
@RestController
public class HelloController {

//  @GetMapping("hello")
//  @PostMapping
//  @DeleteMapping
//  @PutMapping
//  public Object hello(){
//    return "Hello SpringBootqweqweqweqe";
////    return GraceJSONResult.errorCustom(ResponseStatusEnum.SYSTEM_ERROR_GLOBAL);
  @Autowired
  private SMSUtils smsUtils;

  @GetMapping("sms")
  public Object sms() throws Exception {
    String code = "123456";  //模版变量只能传入0 - 6位（包括6位）纯数字
    smsUtils.sendSMS("4255057361",code);
    log.info(code);
    return GraceJSONResult.ok();
  }
}

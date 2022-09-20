package com.wuhao.controller;

import com.wuhao.MinIOConfig;
import com.wuhao.grace.result.GraceJSONResult;
import com.wuhao.utils.MinIOUtils;
import com.wuhao.utils.SMSUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Api(tags = "File upload 测试的接口")
@RestController
public class FileController {
  @Autowired
  private MinIOConfig minIOConfig;

//  @PostMapping("upload")
//  public GraceJSONResult upload(MultipartFile file) throws Exception {
//
//    String fileName = file.getOriginalFilename();
//
//    MinIOUtils.uploadFile(minIOConfig.getBucketName(),
//                          fileName,
//                          file.getInputStream());
//
//    String imgUrl = minIOConfig.getFileHost() + "/" +minIOConfig.getBucketName()+"/" +fileName;
//
//    return GraceJSONResult.ok(imgUrl);
//  }
}

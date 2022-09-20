package com.wuhao.controller;

import com.wuhao.base.BaseInfoProperties;
import com.wuhao.bo.VlogBO;
import com.wuhao.grace.result.GraceJSONResult;
import com.wuhao.pojo.Vlog;
import com.wuhao.service.VlogService;
import com.wuhao.utils.PagedGridResult;
import com.wuhao.utils.SMSUtils;
import com.wuhao.vo.IndexVlogVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "VlogController 测试的接口")
@RestController
@RequestMapping("vlog")
public class VlogController extends BaseInfoProperties {
  @Autowired
  private VlogService vlogService;

  @PostMapping("publish")
  public GraceJSONResult publish(@RequestBody VlogBO vlogBO) {
    vlogService.createVlog(vlogBO);
    return GraceJSONResult.ok();
  }

  @GetMapping("indexList")
  public GraceJSONResult indexList(@RequestParam(defaultValue = "") String search,
                                   @RequestParam Integer page,
                                   @RequestParam Integer pageSize) {

    if (page == null) {
      page = COMMON_START_PAGE;
    }
    if (pageSize == null) {
      pageSize = COMMON_PAGE_SIZE;
    }

    PagedGridResult pagedGridResult  = vlogService.getIndexVlogList(search, page, pageSize);

    return GraceJSONResult.ok(pagedGridResult);
  }

}

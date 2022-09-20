package com.wuhao.service;

import com.wuhao.bo.VlogBO;
import com.wuhao.utils.PagedGridResult;
import com.wuhao.vo.IndexVlogVO;
import com.wuhao.pojo.Vlog;

import java.util.List;

public interface VlogService {

    /**
     * 新增vlog视频
     */
    public void createVlog(VlogBO vlogBO);

    /**
     * 查询首页/搜索的vlog列表
     */
    public PagedGridResult getIndexVlogList(String search, Integer page, Integer pageSize);

}

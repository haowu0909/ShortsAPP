package com.wuhao.service.impl;


import com.github.pagehelper.PageHelper;
import com.wuhao.base.BaseInfoProperties;
import com.wuhao.bo.VlogBO;
import com.wuhao.enums.YesOrNo;
import com.wuhao.mapper.VlogMapper;
import com.wuhao.mapper.VlogMapperCustom;
import com.wuhao.pojo.Vlog;
import com.wuhao.service.VlogService;
import com.wuhao.utils.PagedGridResult;
import com.wuhao.vo.IndexVlogVO;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VlogServiceImpl extends BaseInfoProperties implements VlogService {

    @Autowired
    private VlogMapper vlogMapper;

    @Autowired
    private VlogMapperCustom vlogMapperCustom;

    @Autowired
    private Sid sid;

    @Transactional
    @Override
    public void createVlog(VlogBO vlogBO) {

        String vid = sid.nextShort();

        Vlog vlog = new Vlog();
        BeanUtils.copyProperties(vlogBO, vlog);

        vlog.setId(vid);

        vlog.setLikeCounts(0);
        vlog.setCommentsCounts(0);
        vlog.setIsPrivate(YesOrNo.NO.type);

        vlog.setCreatedTime(new Date());
        vlog.setUpdatedTime(new Date());

        vlogMapper.insert(vlog);
    }

    @Override
    public PagedGridResult getIndexVlogList(String search, Integer page, Integer pageSize) {

        PageHelper.startPage(page, pageSize);

        Map<String, Object> map = new HashMap<>();

        if(StringUtils.isNotBlank(search)){
            map.put("search", search);
        }

         List<IndexVlogVO> list= vlogMapperCustom.getIndexVlogList(map);
//        return list;
        return setterPagedGrid(list, page);
    }


}

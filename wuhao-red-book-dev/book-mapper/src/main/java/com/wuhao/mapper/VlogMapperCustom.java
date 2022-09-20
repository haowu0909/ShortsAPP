package com.wuhao.mapper;

import com.wuhao.vo.IndexVlogVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VlogMapperCustom{

  public List<IndexVlogVO> getIndexVlogList(@Param("paramMap") Map<String, Object> map);
}
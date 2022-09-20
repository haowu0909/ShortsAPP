package com.wuhao.mapper;

import com.wuhao.my.mapper.MyMapper;
import com.wuhao.pojo.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends MyMapper<Comment> {
}
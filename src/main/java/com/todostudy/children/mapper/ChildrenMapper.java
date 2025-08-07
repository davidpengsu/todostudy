package com.todostudy.children.mapper;

import com.todostudy.children.vo.ChildrenVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChildrenMapper {
    void save(ChildrenVO childrenVO);
    List<ChildrenVO> findByUserId(String userId);
    int update(ChildrenVO childrenVO);
    int delete(ChildrenVO childrenVO);
}

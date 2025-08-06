package com.todostudy.children.service;

import com.todostudy.children.mapper.ChildrenMapper;
import com.todostudy.children.vo.ChildrenVO;
import com.todostudy.cmn.ListResVO;
import com.todostudy.cmn.ObjResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildrenService {
    private final ChildrenMapper childrenMapper;

    //등록하는거
    public ObjResVO<Integer> register(ChildrenVO childrenVO, String userId) {
        childrenVO.setUserId(userId);
        childrenVO.setRegDate(LocalDateTime.now().toString());
        childrenVO.setDelYn("N");
        childrenMapper.save(childrenVO);
        return ObjResVO.<Integer>builder()
                .message(1)
                .build();
    }

    //조회
    public ListResVO<ChildrenVO> getChildrenList(String userId) {
        List<ChildrenVO> childrenList = childrenMapper.findByUserId(userId);
        return ListResVO.<ChildrenVO>builder()
                .size(childrenList.size())
                .datas(childrenList)
                .build();
    }

    //수정
    public ObjResVO<Integer> updateChildren(ChildrenVO childrenVO, String userId) {
        childrenVO.setUserId(userId);
        childrenMapper.update(childrenVO);
        return ObjResVO.<Integer>builder().message(1).build();
    }

    //자녀정보딜리트
    public ObjResVO<Integer> deleteChildren(ChildrenVO childrenVO, String userId) {
        childrenVO.setUserId(userId);
        childrenVO.setDelYn("Y");
        childrenMapper.delete(childrenVO);
        return ObjResVO.<Integer>builder().message(1).build();
    }
}

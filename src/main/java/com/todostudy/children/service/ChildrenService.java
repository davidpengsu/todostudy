package com.todostudy.children.service;

import com.todostudy.children.mapper.ChildrenMapper;
import com.todostudy.children.vo.ChildrenVO;
import com.todostudy.cmn.ListResVO;
import com.todostudy.cmn.ObjResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ChildrenService {
    private final ChildrenMapper childrenMapper;

    //등록하는거
    public ObjResVO<String> register(ChildrenVO childrenVO, String userId) {
        childrenVO.setUserId(userId);
        childrenVO.setRegDate(LocalDateTime.now().toString());
        childrenVO.setDelYn("N");
        System.out.println("ㅇㅇㅇㅇ");
        childrenMapper.save(childrenVO);
        return ObjResVO.<String >builder()
                .message("칠드런등록됨!")
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
    public ObjResVO<Integer> updateChild(ChildrenVO childrenVO, String userId) {
        childrenVO.setUserId(userId);

        int updatedRows = childrenMapper.update(childrenVO);
        if (updatedRows == 0){
            return ObjResVO.<Integer>builder()
                    .message(0)
                    .build();
        }
        return ObjResVO.<Integer>builder()
                .message(1)
                .build();
    }


    //자녀정보딜리트
    public ObjResVO<Integer> deleteChild(ChildrenVO childrenVO, String userId) {
        childrenVO.setUserId(userId);
        int updatedRows = childrenMapper.delete(childrenVO);
        if (updatedRows == 0){
            return ObjResVO.<Integer>builder()
                    .message(0)
                    .build();
        }
        childrenVO.setDelYn("Y");
        return ObjResVO.<Integer>builder()
                .message(1)
                .build();
    }
}

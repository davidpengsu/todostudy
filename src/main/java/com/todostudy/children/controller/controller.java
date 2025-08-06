package com.todostudy.children.controller;

import com.todostudy.children.service.ChildrenService;
import com.todostudy.children.vo.ChildrenVO;
import com.todostudy.cmn.ListResVO;
import com.todostudy.cmn.ObjResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/children")
@RequiredArgsConstructor
public class controller {
    private final ChildrenService childrenService;

    //등록하는거
    @PostMapping("/register")
    public ResponseEntity<ObjResVO<Integer>> registerChildren(@RequestBody ChildrenVO childrenVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(childrenService.register(childrenVO, userId));
    }

    //리스트확인
    @PostMapping("/list")
    public ResponseEntity<ListResVO<ChildrenVO>> getChildrenList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(childrenService.getChildrenList(userId));

    }

    //수정
    @PostMapping("/update")
    public ResponseEntity<ObjResVO<Integer>> updateChild(@RequestBody ChildrenVO childrenVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(childrenService.updateChildren(childrenVO, userId));
    }

    //삭제
    @PostMapping("/delete")
    public ResponseEntity<ObjResVO<Integer>> deleteChild(@RequestBody ChildrenVO childrenVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(childrenService.deleteChildren(childrenVO, userId));
    }
}

package com.todostudy.children.controller;

import com.todostudy.children.service.ChildrenService;
import com.todostudy.children.vo.ChildrenVO;
import com.todostudy.cmn.ListResVO;
import com.todostudy.cmn.ObjResVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class ChildrenController {
    private final ChildrenService childrenService;

    //등록하는거
    @PostMapping("/register")
    public ResponseEntity<ObjResVO<Integer>> registerChildren(@RequestBody @Valid ChildrenVO childrenVO) {
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
    public ResponseEntity<ObjResVO<String>> updateChild(@RequestBody ChildrenVO childrenVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        ObjResVO<Integer> serviceResult = childrenService.updateChild(childrenVO, userId);
        if (serviceResult.getMessage() == 1){
            return ResponseEntity.ok(ObjResVO.<String>builder().message("수정됨").build());
        } else {
            // db에 못꽃은거
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ObjResVO.<String>builder().message("수정 안된거임").build());
        }

    }


    //삭제
    @PostMapping("/delete")
    public ResponseEntity<ObjResVO<String>> deleteChild(@RequestBody ChildrenVO childrenVO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();

        ObjResVO<Integer> serviceResult = childrenService.updateChild(childrenVO, userId);
        if (serviceResult.getMessage() == 1){
            return ResponseEntity.ok(ObjResVO.<String>builder().message("삭제된거임").build());
        } else {
            // db에 못꽃은거
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ObjResVO.<String>builder().message("삭제 안된거임").build());
        }

    }
}

package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService service;

    //댓글 등록

    @PostMapping("/comments")
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno , HttpSession session) {
        //String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        System.out.println("dto = " + dto);
        try {
            if(service.write(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR",HttpStatus.BAD_REQUEST);
        }
    }

    //댓글 수정

    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno , @RequestBody CommentDto dto, Integer bno ) {
        //String commenter = (String)session.getAttribute("id");
        dto.setCno(cno);
        System.out.println("dto = " + dto);
        try {
            if(service.modify(dto)!=1)
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK",HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR",HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글 삭제
    @DeleteMapping("/comments/{cno}")

    public ResponseEntity<String> remove(@PathVariable Integer cno , Integer bno , HttpSession session){
//        String commenter = (String)session.getAttribute("id");
          String commenter = "qwer";

        try {
            int rowCnt= service.remove(cno, bno, commenter);

            if (rowCnt != 1)
                throw new Exception("Delete Failed");
            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }


    //지정된 게시물 댓글 가져오기
    @GetMapping("/comments")
     public ResponseEntity<List<CommentDto>> list(Integer bno){
        List<CommentDto> list = null;
        try {
            list = service.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);

        }

    }
}

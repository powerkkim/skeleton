package com.powernote.skeleton.controller.api;

import com.powernote.skeleton.dto.PageInfoDto;
import com.powernote.skeleton.dto.PostDataDto;
import com.powernote.skeleton.dto.ResponseDto;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.service.BasicBoardService;
import com.powernote.skeleton.vo.PostDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
public class ApiBacisBoardController {
    @Autowired
    BasicBoardService boardService;


    @PostMapping("write")
    public ResponseEntity c_boardWrite( @RequestBody PostDataDto postData) {

        boardService.write(postData);

        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
    }

    @GetMapping("list")
    public ResponseEntity r_list( PageInfoDto rPageInfo ) {
        Page<PostDataVo> dtoPages  = boardService.read(rPageInfo);

        ResponseDto responseDto = new ResponseDto(MessageType.OK);
        responseDto.setData(dtoPages);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("update")
    public ResponseEntity u_update( @RequestBody PostDataDto postData) {
        boardService.update(postData);
        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
    }

    @DeleteMapping("delete")
    public ResponseEntity d_update(PostDataDto postData) {
        boardService.delete(postData);
        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
    }

}

package com.powernote.skeleton.powernoteboard.controller.api;

import com.powernote.skeleton.dto.PageInfoDto;
import com.powernote.skeleton.dto.ResponseDto;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.powernoteboard.service.PowerNoteBoardService;
import com.powernote.skeleton.service.BasicBoardService;
import com.powernote.skeleton.vo.PostDataVo;
import com.powernote.skeleton.vo.PowerNotePostDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/powernoteboard")
public class ApiPowerNoteBoardController {
    @Autowired
    PowerNoteBoardService boardService;


    @PostMapping("write")
    public ResponseEntity c_boardWrite( @RequestBody PowerNotePostDataVo postDataVo) {
        boardService.write(postDataVo);
        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
    }

    @GetMapping("list")
    public ResponseEntity r_list( PageInfoDto rPageInfo ) {
        Page<PowerNotePostDataVo> dtoPages  = boardService.read(rPageInfo);

        ResponseDto responseDto = new ResponseDto(MessageType.OK);
        responseDto.setData(dtoPages);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("update")
    public ResponseEntity u_update( @RequestBody PowerNotePostDataVo postDataVo) {
        boardService.update(postDataVo);
        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
    }

    @DeleteMapping("delete")
    public ResponseEntity d_update(PowerNotePostDataVo postDataVo) {
        boardService.delete(postDataVo);
        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
    }

}

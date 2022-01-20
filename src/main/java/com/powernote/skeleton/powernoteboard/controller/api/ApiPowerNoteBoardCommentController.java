package com.powernote.skeleton.powernoteboard.controller.api;

import com.powernote.skeleton.powernoteboard.dto.PowerNoteCommentDataDto;
import com.powernote.skeleton.dto.ResponseDto;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.powernoteboard.service.PowerNoteBoardCommentService;
import com.powernote.skeleton.powernoteboard.vo.PowerNoteCommentDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/powernoteboard/Comment")
public class ApiPowerNoteBoardCommentController {
    @Autowired
    PowerNoteBoardCommentService boardCommentService;


    @PostMapping("write")
    public ResponseEntity c_boardWrite( @RequestBody PowerNoteCommentDataDto commentData) {
        PowerNoteCommentDataVo responseData = boardCommentService.write(commentData);

        return ResponseEntity.ok(  ResponseDto.builder()
                                                .result(MessageType.OK.toString())
                                                .message(MessageType.OK.getMessage())
                                                .data( responseData )
                                                .build() );
    }

//    @GetMapping("list")
//    public ResponseEntity r_list( PageInfoDto rPageInfo ) {
//        Page<PowerNotePostDataVo> dtoPages  = boardService.read(rPageInfo);
//
//        ResponseDto responseDto = new ResponseDto(MessageType.OK);
//        responseDto.setData(dtoPages);
//
//        return ResponseEntity.ok(responseDto);
//    }
//
//    @PutMapping("update")
//    public ResponseEntity u_update( @RequestBody PowerNotePostDataVo postDataVo) {
//        boardService.update(postDataVo);
//        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
//    }
//
//    @DeleteMapping("delete")
//    public ResponseEntity d_update(PowerNotePostDataVo postDataVo) {
//        boardService.delete(postDataVo);
//        return ResponseEntity.ok( new ResponseDto(MessageType.OK) );
//    }

}

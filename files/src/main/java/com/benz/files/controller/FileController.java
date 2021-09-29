package com.benz.files.controller;

import com.benz.files.controller.dto.DataDTO;
import com.benz.files.controller.dto.DataDTOBuf;
import com.benz.files.controller.dto.FileType;
import com.benz.files.controller.dto.StatusResponseDTO;
import com.benz.files.util.ConversionUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * Controller for file operations
 */
@Validated
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<StatusResponseDTO> store(@RequestBody @Valid DataDTO data, @RequestHeader FileType fileType) {
        DataDTOBuf.Data d = ConversionUtils.toBuf(data);
        rabbitTemplate.convertAndSend("files", d.toByteString().toString(StandardCharsets.UTF_8));
        return ResponseEntity.ok(StatusResponseDTO.SUCCESS);
    }

    @PutMapping
    public ResponseEntity<StatusResponseDTO> update(@RequestBody DataDTO data) {
        return ResponseEntity.ok(StatusResponseDTO.SUCCESS);
    }

    @GetMapping
    public ResponseEntity<DataDTO> read() {
        return ResponseEntity.ok(null);
    }
}

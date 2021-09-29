package com.assessment.files.controller;

import com.assessment.files.controller.dto.*;
import com.assessment.files.util.ConversionUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Controller for file operations
 */
@Validated
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<StatusResponseDTO> store(@RequestBody @Valid DataDTO data, @RequestHeader FileType fileType) {
        DataDTOBuf.Data d = ConversionUtils.toBuf(data, fileType);
        rabbitTemplate.convertAndSend("files", d.toByteString().toString(StandardCharsets.UTF_8));
        return ResponseEntity.ok(StatusResponseDTO.SUCCESS);
    }

    @PutMapping
    public ResponseEntity<StatusResponseDTO> update(@RequestBody DataDTO data, @RequestHeader FileType fileType) {
        DataDTOBuf.Data d = ConversionUtils.toBuf(data, fileType);
        rabbitTemplate.convertAndSend("files", d.toByteString().toString(StandardCharsets.UTF_8));
        return ResponseEntity.ok(StatusResponseDTO.SUCCESS);
    }

    @GetMapping
    public ResponseEntity<List<DataDTO>> read() {
        return ResponseEntity.ok(restTemplate.getForEntity("http://localhost:8081/storage/", DataDTOList.class).getBody().getDataList());
    }
}

package com.benz.files.controller;

import com.benz.files.controller.dto.DataDTO;
import com.benz.files.controller.dto.FileType;
import com.benz.files.controller.dto.StatusResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for file operations
 */
@Validated
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping
    public ResponseEntity<StatusResponseDTO> store(@RequestBody DataDTO data, @RequestHeader FileType fileType) {
        return ResponseEntity.ok(StatusResponseDTO.SUCCESS);
    }

    @PutMapping
    public ResponseEntity<StatusResponseDTO> update(@RequestBody DataDTO data) {
        return ResponseEntity.ok(StatusResponseDTO.SUCCESS);
    }

    @GetMapping
    public ResponseEntity<String> read() {
        return ResponseEntity.ok("");
    }
}

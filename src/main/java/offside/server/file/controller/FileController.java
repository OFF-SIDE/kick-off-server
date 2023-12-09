package offside.server.file.controller;

import offside.server.file.dto.UrlResponseDto;
import offside.server.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    private final FileService fileService;
    
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    
    @PostMapping("image")
    @ResponseBody
    public UrlResponseDto postImg(@RequestParam("file") MultipartFile file){
        return new UrlResponseDto(fileService.store(file));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
}

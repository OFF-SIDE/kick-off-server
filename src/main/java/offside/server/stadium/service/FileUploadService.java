package offside.server.stadium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

@Service
public class FileUploadService {
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public String store(MultipartFile file) {
        validateFile(file); // 1번
        String fileName = UUID.randomUUID().toString();
        String path = "src/main/resources/images" + fileName;
        try {
            file.transferTo(new File(path));
        }catch (Exception e){
            throw new IllegalStateException("해당 파일을 저장할 수 없습니다.");
        }
        return "";
    }


    private static void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("빈 파일을 저장할 수 없습니다.");
        }
    }
}

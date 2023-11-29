package offside.server.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    private final ResourceLoader resourceLoader;
    
    @Value("${server.url}")
    private String serverUrl;
    @Value("${server.imageDirectoryPath}")
    private String imageDirectoryPath;

    @Autowired
    public FileService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    public String store(MultipartFile file) {
        validateFile(file); // 1번
        
        String newFileName = UUID.randomUUID().toString() + this.getExtensionFromFile(file);
        String newFilePath = imageDirectoryPath + newFileName;
        System.out.println(newFilePath);
        
        try {
            file.transferTo(new File(newFilePath));
            return serverUrl + "/image/"+newFileName;
        }catch (Exception e){
            throw new IllegalStateException("해당 파일을 저장할 수 없습니다.");
        }
    }
    
    public Resource getImage(String imageName) {
        System.out.println(System.getProperty("user.dir") + "/image/" + imageName);
        Resource resource = resourceLoader.getResource("../../../image/" + imageName);
        if(resource.exists()){
            return resource;
        }else {
            throw new IllegalStateException("해당 경로에 파일을 찾을 수 없습니다");
        }
    }
    
    public String getExtensionFromFile(MultipartFile file){
        String fileOriginalName=  file.getOriginalFilename();
        Integer idx = fileOriginalName.lastIndexOf(".");
        if (idx > 0){
            return fileOriginalName.substring(idx);
        } else{
            return ".jpg";
        }
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("빈 파일을 저장할 수 없습니다.");
        }
    }
}

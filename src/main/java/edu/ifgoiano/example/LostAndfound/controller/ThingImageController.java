package edu.ifgoiano.example.LostAndfound.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.ifgoiano.example.LostAndfound.service.FileStorageService;
import edu.ifgoiano.example.LostAndfound.vo.UploadFileResponseVO;

@RestController
@RequestMapping("/api/thingImage/")
public class ThingImageController 
{
    private Logger logger = Logger.getLogger( ThingImageController.class.getName() );

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) 
    {
        logger.info("Storingfile todisk");

        var filename = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/file/v1/downloadFile/")
                                .path(filename)
                                .toUriString();
                    
        return new UploadFileResponseVO(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }
}

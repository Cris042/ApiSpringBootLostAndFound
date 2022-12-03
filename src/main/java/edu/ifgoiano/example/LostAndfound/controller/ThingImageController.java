package edu.ifgoiano.example.LostAndfound.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;

import edu.ifgoiano.example.LostAndfound.exceptions.others.NotFoundException;
import edu.ifgoiano.example.LostAndfound.models.Image;
import edu.ifgoiano.example.LostAndfound.models.Thing;
import edu.ifgoiano.example.LostAndfound.service.FileStorageService;
import edu.ifgoiano.example.LostAndfound.service.ImageService;
import edu.ifgoiano.example.LostAndfound.service.ThingService;
import edu.ifgoiano.example.LostAndfound.vo.UploadFileResponseVO;


@RestController
@RequestMapping("/api/thing")
public class ThingImageController 
{
    private Logger logger = Logger.getLogger( ThingImageController.class.getName() );

    @Autowired
    private FileStorageService fileStorageService;
 
    @Autowired
    ThingService thingService;

    @Autowired
    ImageService imageService;

    @PostMapping("/uploadFile/{id}")
    public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") UUID id ) 
    {
        logger.info("Storingfile todisk");

        Optional<Thing> obj = thingService.findByThingID(id);

        if (!obj.isPresent()) 
        {
            throw new NotFoundException("Thing Not found!.");
        }

        Set<Image> image = new HashSet<>();

        var filename = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/thing/downloadFile/")
        .path(filename)
        .toUriString();

        var imageEntities = new Image( filename, fileDownloadUri );

        imageService.save( imageEntities );
        image.add(imageEntities);
 
        var thingEntities = new Thing();
        BeanUtils.copyProperties( obj.get(), thingEntities);  
        thingEntities.setDateUpdate(LocalDateTime.now( ZoneId.of("America/Sao_Paulo")) );
        thingEntities.setImegens(image); 

        thingService.save( thingEntities );

                    
        return new UploadFileResponseVO(filename, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultFile")
    public List<UploadFileResponseVO> uploadMultFile(@RequestParam("files") MultipartFile[] files, @PathVariable(value = "id") UUID id) 
    {
        logger.info("Storingfiles todisk");

        return Arrays.asList(files).stream().map(file-> uploadFile(file, id)).collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) 
    {
        Resource resource = fileStorageService.loadFileAsResource(filename);
        String contentType= "";

        try
        { 
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } 
        catch(Exception e)
        {
            logger.info("Could not determine file type!");
        }

        if(contentType.isBlank()) contentType= "application/octet-stream";

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
               .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename() + "\"").body(resource);

    }

}


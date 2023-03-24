package ru.lernup.socialnetwork.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.lernup.socialnetwork.service.ContentService;

import java.io.*;

@RestController
@RequestMapping("/file")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping("/{id_user}")
    public void addImage(@RequestParam("file")MultipartFile file,
                         @PathVariable("id_user") Long idUser) throws IOException {
       contentService.addFile(file,idUser);
    }
    @GetMapping(
            value = "/img",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@RequestParam("name")String name,
                                                      @RequestParam(value = "id",required = false)Long id ) throws IOException {
        String error;
        try{
        if (id==null){
            return contentService.getImgFileByName(name);
        }
        return contentService.getImgFileByPersonAndName(name,id);}
        catch (FileNotFoundException e){
            error = "Файл не найден";
        }
        return error.getBytes();
    }
    @GetMapping(value = "/doc",produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody byte[] getDocFile(@RequestParam("name")String name,
                                           @RequestParam(value = "id",required = false)Long id ) throws IOException {
        String error;
        try{
            if (id==null){
                return contentService.getDocFileByName(name);
            }
            return contentService.getDocFileByPersonAndName(name,id);}
        catch (FileNotFoundException e){
            error = "Файл не найден";
        }
        return error.getBytes();
    }


}

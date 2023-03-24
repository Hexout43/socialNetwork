package ru.lernup.socialnetwork.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Repository.FileRepository;

@Component
public class GenerateContent {
    private final FileRepository contentRepository;
    @Value("${server.port}")
    private String port;

    public GenerateContent(FileRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public String generateUrl(FilePerson filePerson){
        if (findImageFile(filePerson)!=null){
            return "http://localhost:" +port + "/file/img?name=" +

                    filePerson.getName();
        }
        else  if (findDocFile(filePerson)!=null){
            return "http://localhost:" +port + "/file/doc?name=" +
                    filePerson.getName();
        }
        return null;
    }
    public String findImageFile(FilePerson filePerson){
        String[] namedFile = filePerson.getName().split("\\.");
        switch (namedFile[namedFile.length - 1].toLowerCase()) {
            case "img", "jpg", "jpeg", "png" -> {
                return filePerson.getName();
            }
            default -> {
            }
        }
        return null;
    }
    public String findDocFile(FilePerson filePerson){
        String[] namedFile = filePerson.getName().split("\\.");
        switch (namedFile[namedFile.length - 1].toLowerCase()) {
            case "doc", "txt" -> {
                return filePerson.getName();
            }
            default -> {
            }
        }
        return null;
    }
    public FilePerson generateContent(String url){
        String[] words = url.split("/");
        int prefix = words[words.length-1].indexOf("=");
        String name = words[words.length-1].substring(prefix+1);
        return
        contentRepository.getFilePersonByNameContains(name);

    }

}

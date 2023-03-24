package ru.lernup.socialnetwork.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.lernup.socialnetwork.Db.Entity.FilePerson;
import ru.lernup.socialnetwork.Db.Repository.FileRepository;
import ru.lernup.socialnetwork.Db.Repository.PersonRepository;

import java.io.*;
import java.util.UUID;
@Service
public class ContentService {
    private final FileRepository fileRepository;
    private final PersonRepository personRepository;
    private final GenerateContent generateContent;
    @Value("${upload.path}")
    private String uploadPath;

    public ContentService(FileRepository fileRepository,
                          PersonRepository personRepository,
                          GenerateContent generateContent) {
        this.fileRepository = fileRepository;
        this.personRepository = personRepository;
        this.generateContent = generateContent;
    }
    public void addFile(MultipartFile file,Long id) throws IOException {
        if(file!=null){
            File upDir = new File(uploadPath);
            if(upDir.exists()){
                upDir.mkdir();
            }
           String allName = generateName(file);
            fileRepository.save(generateFilePerson(allName,id));
            file.transferTo(new File(generatePath(allName)));
        }
    }
    private String generateName(MultipartFile file){
        String uuidFile= UUID.randomUUID().toString();
        String resultFileName = uuidFile  + "." + file.getOriginalFilename();
        return  resultFileName;
    }
    private String generatePath(String name){
        return uploadPath + "/" + name;
    }
    private FilePerson generateFilePerson(String name,Long id){
        FilePerson filePerson = new FilePerson();
        filePerson.setName(name);
        filePerson.setPerson(personRepository.getReferenceById(id));
        return filePerson;
    }
    public byte[] getImgFileByName(String name) throws IOException {
      String namedFile = generateContent.findImageFile(getFilePersonByName(name));
      return outputFile(namedFile);
    }
    public byte[] getImgFileByPersonAndName(String name ,Long id) throws IOException{
        String namedFile = generateContent.findImageFile(getFilePersonByPersonAndName(name,id));
        return outputFile(namedFile);
    }
    public byte[] getDocFileByName(String name) throws IOException {
        String namedFile= generateContent.findDocFile(getFilePersonByName(name));
        return
                outputFile(namedFile);
    }
    public  byte[] getDocFileByPersonAndName(String name,Long id) throws IOException {
        String namedFile = generateContent.findDocFile(getFilePersonByPersonAndName(name,id));
        return
                outputFile(namedFile);
    }
    private FilePerson getFilePersonByName(String name){
        return fileRepository.getFilePersonByNameContains(name);
    }
    private FilePerson getFilePersonByPersonAndName(String name,Long id){
        return fileRepository.getFilePersonByPersonAndNameContains(personRepository.getReferenceById(id),name);
    }
    private byte[] outputFile(String nameFile) throws IOException {
        if(nameFile==null){
            throw new FileNotFoundException();
        }
        InputStream in = new FileInputStream(generatePath(nameFile));
        return IOUtils.toByteArray(in);
    }


}

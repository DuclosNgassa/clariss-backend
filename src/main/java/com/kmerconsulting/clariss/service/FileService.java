package com.kmerconsulting.clariss.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    public void deleteFile(String fileName) {
        Path fileToDeletePath = Paths.get(System.getProperty("user.dir"), fileName);
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            //TODO LOGGER
            e.printStackTrace();
        }
    }

}

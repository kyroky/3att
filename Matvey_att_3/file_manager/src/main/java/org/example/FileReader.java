package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public FileReader() {
    }

    public List<Player> readFile(String fileName){
        try {
            return new ObjectMapper().readValue(new FileInputStream(new File(fileName)),new TypeReference<List<Player>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

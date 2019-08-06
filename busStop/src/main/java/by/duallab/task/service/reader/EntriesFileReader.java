package by.duallab.task.service.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class EntriesFileReader {

    private EntriesFileReader(){
        super();
    }

    private static class SingletonHandler{
        static final EntriesFileReader instance = new EntriesFileReader();
    }

    public static EntriesFileReader getInstance(){
        return SingletonHandler.instance;
    }

    public List<String> read(String filePath) throws IOException {
        List<String> fileLines = new LinkedList<>();
        try(Stream<String> stream = Files.lines(new File(filePath).toPath())) {
            stream.forEach(fileLines::add);
        } catch (IOException e){
            throw e;
        }
        return fileLines;
    }

}

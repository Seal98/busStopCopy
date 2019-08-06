package by.duallab.task.service.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EntriesFileWriter {

    public void write(String timetable) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        bufferedWriter.write(timetable);
        bufferedWriter.close();
    }

}

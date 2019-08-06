package by.duallab.task.controller;

import by.duallab.task.service.generator.TimetableGenerator;
import by.duallab.task.service.writer.EntriesFileWriter;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        String path = args[0];
        TimetableGenerator generator = TimetableGenerator.getInstance();
        try {
            String timetable = generator.getTimetable(path);
            EntriesFileWriter fileWriter = new EntriesFileWriter();
            fileWriter.write(timetable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package by.duallab.task.service.parser;

import by.duallab.task.beans.Entry;
import by.duallab.task.beans.JourneyTimeInterval;
import by.duallab.task.beans.Time;
import by.duallab.task.service.consts.ServiceKeys;
import by.duallab.task.service.validator.ValidateEntryLine;

import java.util.LinkedList;
import java.util.List;

public class ParseEntryLines {

    private ParseEntryLines(){
        super();
    }

    private static class SingletonHandler{
        static final ParseEntryLines instance = new ParseEntryLines();
    }

    public static ParseEntryLines getInstance(){
        return ParseEntryLines.SingletonHandler.instance;
    }

    public List<Entry> parse(List<String> entryLines){
        List<Entry> entries = new LinkedList<>();
        ValidateEntryLine validator = ValidateEntryLine.getInstance();
        entryLines.forEach(line -> {
            if(validator.validate(line)){
                String[] lexemes = line.split(ServiceKeys.EMPTY_STRING);
                String[] departureTime = lexemes[1].split(ServiceKeys.TIME_DELIMITER);
                String[] arrivalTime = lexemes[2].split(ServiceKeys.TIME_DELIMITER);
                entries.add(new Entry(
                        new JourneyTimeInterval(new Time(departureTime[0], departureTime[1]),
                                new Time(arrivalTime[0], arrivalTime[1])),
                        lexemes[0]));
            }
        });
        return entries;
    }

}

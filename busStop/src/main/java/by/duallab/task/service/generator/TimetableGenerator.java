package by.duallab.task.service.generator;

import by.duallab.task.beans.Entry;
import by.duallab.task.dao.Dao;
import by.duallab.task.dao.EntryDao;
import by.duallab.task.service.consts.ServiceKeys;
import by.duallab.task.service.filter.EntriesFilter;
import by.duallab.task.service.parser.ParseEntryLines;
import by.duallab.task.service.reader.EntriesFileReader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class TimetableGenerator {

    private TimetableGenerator(){
        super();
    }

    private static class SingletonHandler{
        static final TimetableGenerator instance = new TimetableGenerator();
    }

    public static TimetableGenerator getInstance(){
        return SingletonHandler.instance;
    }

    public String getTimetable(String filePath) throws IOException {
        String timetable = ServiceKeys.EMPTY_STRING;
        EntriesFileReader reader = EntriesFileReader.getInstance();
        List<String> fileLines = reader.read(filePath);
        ParseEntryLines parser = ParseEntryLines.getInstance();
        List<Entry> entries = parser.parse(fileLines);
        Dao<Entry> dao = EntryDao.getInstance();
        dao.set(entries);
        filterEntries(entries);
        timetable = createTimetable(entries);
        return timetable;
    }

    private String createTimetable(List<Entry> entries){
        StringBuilder timetable = new StringBuilder();
        boolean delimiterUsed = false;
        for (Entry entry : entries) {
            if(entry.getCompanyName().compareTo(ServiceKeys.COMPANY_NAME_GROTTY) == 0 && !delimiterUsed){
                timetable.append(ServiceKeys.NEXT_LINE_SYMBOL);
                delimiterUsed = true;
            }
            timetable.append(entry).append(ServiceKeys.NEXT_LINE_SYMBOL);
        }
        return timetable.toString();
    }

    private void filterEntries(List<Entry> entries){
        EntriesFilter filter = EntriesFilter.getInstance();
        filter.journeyLengthFilter(entries);
        filter.timeEqualityFilter(entries);
        filter.timeProfitabilityFilter(entries);
        sortEntries(entries);
    }

    private void sortEntries(List<Entry> entries){
        List<Entry> grottyEntries = new LinkedList<>();
        entries.sort((entry1, entry2) -> {
            int entryDepartureTime1 = entry1.getTimeInterval().getDepartureTime().hashCode();
            int entryDepartureTime2 = entry2.getTimeInterval().getDepartureTime().hashCode();
            return Integer.compare(entryDepartureTime1, entryDepartureTime2);
        });
        entries.forEach(entry -> {
            if(entry.getCompanyName().compareTo(ServiceKeys.COMPANY_NAME_GROTTY) == 0){
                grottyEntries.add(entry);
            }
        });
        entries.removeAll(grottyEntries);
        entries.addAll(grottyEntries);
    }
}

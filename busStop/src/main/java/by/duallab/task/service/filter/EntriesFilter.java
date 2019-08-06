package by.duallab.task.service.filter;

import by.duallab.task.beans.Entry;
import by.duallab.task.beans.JourneyTimeInterval;
import by.duallab.task.service.consts.ServiceKeys;
import by.duallab.task.service.generator.TimetableGenerator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EntriesFilter {

    private EntriesFilter(){
        super();
    }

    private static class SingletonHandler{
        static final EntriesFilter instance = new EntriesFilter();
    }

    public static EntriesFilter getInstance(){
        return EntriesFilter.SingletonHandler.instance;
    }

    public void journeyLengthFilter(List<Entry> entries){
        for (Iterator<Entry> iterator = entries.iterator(); iterator.hasNext(); ) {
            Entry entry = iterator.next();
            int journeyLength = entry.getTimeInterval().getJourneyLength();
            if(journeyLength > 100){
                iterator.remove();
            }
        }
    }

    public void timeEqualityFilter(List<Entry> entries){
        List<Entry> entriesForRemove = new LinkedList<>();
        entries.forEach(entry1 -> entries.forEach(entry2 -> {
            if(!entry1.equals(entry2)){
                if(entry1.getTimeInterval().hashCode() == entry2.getTimeInterval().hashCode()){
                    if(entry1.getCompanyName().compareTo(ServiceKeys.COMPANY_NAME_POSH) == 0){
                        entriesForRemove.add(entry2);
                    } else {
                        entriesForRemove.add(entry1);
                    }
                }
            }
        }));
        entries.removeAll(entriesForRemove);
    }

    public void timeProfitabilityFilter(List<Entry> entries){
        List<Entry> entriesForRemove = new LinkedList<>();
        for (Entry entry1 : entries) {
            for (Entry entry2 : entries) {
                if (!entry1.equals(entry2)) {
                    if (entry1.getTimeInterval().getDepartureTime().equals(entry2.getTimeInterval().getDepartureTime())) {
                        if (journeyLengthProfitabilityFilter(entries, entry1, entry2, entriesForRemove)){
                            break;
                        }
                    } else if (entry1.getTimeInterval().getArrivalTime().equals(entry2.getTimeInterval().getArrivalTime())) {
                        if (journeyLengthProfitabilityFilter(entries, entry1, entry2, entriesForRemove)){
                            break;
                        }
                    } else if (isSubsequenceOfInterval(entry1.getTimeInterval(), entry2.getTimeInterval())){
                        entriesForRemove.add(entry2);
                    }
                }
            }
        }
        entries.removeAll(entriesForRemove);
    }

    public boolean isSubsequenceOfInterval(JourneyTimeInterval interval1, JourneyTimeInterval interval2){
        if(interval1.getJourneyLength() > interval2.getJourneyLength()){
            return false;
        }
        int intervalDepartureTime1 = interval1.getDepartureTime().hashCode();
        int intervalDepartureTime2 = interval2.getDepartureTime().hashCode();
        int intervalArrivalTime1 = interval1.getArrivalTime().hashCode();
        int intervalArrivalTime2 = interval1.getArrivalTime().hashCode();
        if(intervalArrivalTime1 < intervalDepartureTime1) {
            intervalArrivalTime1 += 2400;
        }
        if(intervalArrivalTime2 < intervalDepartureTime2){
            intervalArrivalTime2 += 2400;
        }
        if(intervalDepartureTime1 > intervalDepartureTime2 && intervalArrivalTime1 < intervalArrivalTime2){
            return true;
        }
        return false;
    }

    private boolean journeyLengthProfitabilityFilter(List<Entry> entries, Entry entry1, Entry entry2, List<Entry> entriesForRemove) {
        int journeyLength1 = entry1.getTimeInterval().getJourneyLength();
        int journeyLength2 = entry2.getTimeInterval().getJourneyLength();
        if (journeyLength1 > journeyLength2) {
            entriesForRemove.add(entry1);
            return true;
        } else if (journeyLength1 < journeyLength2) {
            entriesForRemove.add(entry2);
        }
        return false;
    }


}

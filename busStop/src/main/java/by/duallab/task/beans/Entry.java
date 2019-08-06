package by.duallab.task.beans;

import by.duallab.task.service.consts.ServiceKeys;

import java.util.Date;

public class Entry {

    private String companyName;
    private JourneyTimeInterval timeInterval;

    public Entry() {
        super();
    }

    public Entry(JourneyTimeInterval timeInterval, String companyName){
        this.timeInterval = timeInterval;
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public JourneyTimeInterval getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(JourneyTimeInterval timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o == this){
            return true;
        }
        if(this.getClass().getName().compareTo(o.getClass().getName()) != 0 ||
            this.hashCode() != o.hashCode()){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return timeInterval.hashCode() + companyName.hashCode();
    }

    @Override
    public String toString(){
        return companyName + " " + timeInterval;
    }
}

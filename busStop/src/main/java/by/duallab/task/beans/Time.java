package by.duallab.task.beans;

import by.duallab.task.service.consts.ServiceKeys;

public class Time {

    private String hours;
    private String minutes;

    public Time(){
        super();
    }

    public Time(String hours, String minutes){
        this.hours = hours;
        this.minutes = minutes;
    }


    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
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
        return Integer.parseInt(hours) * 100 + Integer.parseInt(minutes);
    }

    @Override
    public String toString(){
        return hours + ":" + minutes;
    }

}

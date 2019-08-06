package by.duallab.task.beans;

public class JourneyTimeInterval {

    private Time departureTime;
    private Time arrivalTime;

    public JourneyTimeInterval(){
        super();
    }

    public JourneyTimeInterval(Time departureTime, Time arrivalTime){
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getJourneyLength(){
        int journeyLength = arrivalTime.hashCode() -
                departureTime.hashCode();
        journeyLength = journeyLength > 0 ? journeyLength : arrivalTime.hashCode()
                + (2400 - departureTime.hashCode());
        return journeyLength;
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
        return departureTime.hashCode() * 10000 + arrivalTime.hashCode();
    }

    @Override
    public String toString(){
        return departureTime + " " + arrivalTime;
    }

}

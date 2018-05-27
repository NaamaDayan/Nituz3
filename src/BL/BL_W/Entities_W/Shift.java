package BL.BL_W.Entities_W;

import BL.BL_T.Entities.Place;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Shift {

    private List<Worker> workers;
    private Place place;
    private Date date;

    private Worker shiftManager;

    private String placeId;

    public String getPlaceId() {
        return placeId;
    }

    public enum ShiftDayPart {
        Morning("Morning"),
        Evening("Evening");
        private String name;

        public String toString() {
            return name;
        }

        ShiftDayPart(String name) {
            this.name = name;
        }
    }

    ShiftDayPart shiftDayPart;


    public Shift(Date date, ShiftDayPart shiftDayPart, Worker shiftManager){
        this.date = date;
        this.shiftDayPart = shiftDayPart;
        workers = new LinkedList<>();
        this.shiftManager = shiftManager;
    }


    public Shift(Date date, ShiftDayPart shiftDayPart , Place place) {
        this.place = place;
        this.date = date;
        this.shiftDayPart = shiftDayPart;
        workers = new LinkedList<>();
    }

    public Shift(Date date, ShiftDayPart shiftDayPart , List<Worker> workers , Place place, Worker shiftManager) {
        this.place = place;
        this.date = date;
        this.shiftDayPart = shiftDayPart;
        this.workers = workers;
        this.shiftManager = shiftManager;
    }

    public static ShiftDayPart getDayPartByName(String shiftDayPartName){
        for (ShiftDayPart shift: ShiftDayPart.values()) {
            if (shift.name.toLowerCase().equals(shiftDayPartName.toLowerCase()))
                return shift;
        }
        return null;
    }

    public Date getDate() {
        return date;
    }

    public ShiftDayPart getShiftDayPart() {
        return shiftDayPart;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Shift))
            return false;
        Shift other = (Shift)obj;
        return (this.date.toString().equals(other.date.toString()) && this.workers.equals(other.workers)
                && this.shiftDayPart.name.equals(other.shiftDayPart.name));
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Shift{" +
                "workers=" + workers +
                ", date=" + date +
                ", shiftDayPart=" + shiftDayPart +
                '}';
    }
}

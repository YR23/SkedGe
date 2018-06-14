package skedge.yr.yr.skedge;

/**
 * Created by yardenrotem on 11/04/2017.
 */
public class SerchingClass
{
    private String building,classroom,from,to,name;

    public SerchingClass() {
    }

    public SerchingClass(String building, String classroom, String from, String to, String name) {
        this.building = building;
        this.classroom = classroom;
        this.from = from;
        this.to = to;
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

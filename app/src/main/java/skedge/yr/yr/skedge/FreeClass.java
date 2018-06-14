package skedge.yr.yr.skedge;

/**
 * Created by yardenrotem on 08/04/2017.
 */
public class FreeClass {

    private String building,classroom,until,max,cur;

    public FreeClass(String building, String classroom, String until, String max, String cur) {
        this.building = building;
        this.classroom = classroom;
        this.until = until;
        this.max = max;
        this.cur=cur;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
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

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }


}

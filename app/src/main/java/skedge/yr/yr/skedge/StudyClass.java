package skedge.yr.yr.skedge;

/**
 * Created by yardenrotem on 13/04/2017.
 */
public class StudyClass {
    String name,build,cls,hourF,hourT,day;

    public StudyClass() {
    }

    public StudyClass(String name, String build, String cls, String hourF, String hourT, String day) {
        this.name = name;
        this.build = build;
        this.cls = cls;
        this.hourF = hourF;
        this.hourT = hourT;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getHourF() {
        return hourF;
    }

    public void setHourF(String hourF) {
        this.hourF = hourF;
    }

    public String getHourT() {
        return hourT;
    }

    public void setHourT(String hourT) {
        this.hourT = hourT;
    }

    public String getDay() {
        if (day.equals("1"))
            day="יום ראשון";
        if (day.equals("2"))
            day="יום שני";
        if (day.equals("3"))
            day="יום שלישי";
        if (day.equals("4"))
            day="יום רביעי";
        if (day.equals("5"))
            day="יום חמישי";
        if (day.equals("6"))
            day="יום שישי";
        if (day.equals("7"))
            day="יום שבת";
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

package biao.community.information;

public class Time {
    private String year;
    private String month;
    private String day;
    private String hour;
    private String min;

    public Time(String time){
        this.year = time.substring(0, 4);
        this.month = time.substring(5, 7);
        this.day = time.substring(8, 10);
        this.hour = time.substring(11, 13);
        this.min = time.substring(14, 16);
    }


    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getMin() {
        return min;
    }


}

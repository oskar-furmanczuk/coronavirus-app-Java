import java.util.List;

public class TZachorowania extends TCzynnosc{
    int day = 0;
    int month = 0;
    int year = 0;
    int cases = 0;

    TZachorowania(String arg1, int day, int month, int year, int cases) {
        super(arg1);
        this.day = day;
        this.month = month;
        this.year = year;
        this.cases = cases;
    }

    public int getDay(){
        return this.day;
    }
    public int getMonth(){
        return this.month;
    }
    public int getYear(){
        return this.year;
    }
    public int getCases(){
        return this.cases;
    }

}

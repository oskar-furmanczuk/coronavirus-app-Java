import java.util.ArrayList;

public class TCzynnosc {
    TCzynnosc(String arg){
        this.nazwaCzynnosci = arg;
    }

    public void show(){
        System.out.print(" - " + this.nazwaCzynnosci + " ");
    }
    private String nazwaCzynnosci;
}
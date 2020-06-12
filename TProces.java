import java.util.ArrayList;

public class TProces {
    TProces(){
        listaCzynnosci = new ArrayList<TCzynnosc>();
        this.liczbaCzynnosci = 0;
    }
    public void add(TCzynnosc arg){
        listaCzynnosci.add(arg);
        this.liczbaCzynnosci++;
    }
    public void show(){
        System.out.println("Oto lista " + liczbaCzynnosci +" czynnosci:");
        for (TCzynnosc tymczas : listaCzynnosci)
            tymczas.show();
    }
    public ArrayList<TCzynnosc> getListaCzynnosci(){
        return listaCzynnosci;
    }
    public int getLiczbaCzynnosci(){
        return liczbaCzynnosci;
    }
    private ArrayList<TCzynnosc> listaCzynnosci;
    private int liczbaCzynnosci;
}


import java.util.ArrayList;
import java.util.List;

public class TPandemia extends TProces {

    public double sredniaLiczbaZachorowan(int iloscZachorowan, int iloscDni ) {
        double srednia = 0;
        srednia = iloscZachorowan / iloscDni;
        return srednia;
    }

}
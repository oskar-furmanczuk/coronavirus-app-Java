import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;

public class TBufor {
    public static List<TZachorowania> przeczytajCSV (){
        List<TZachorowania> dane = new ArrayList<>();
        String poland = "Poland";
        String url = "https://opendata.ecdc.europa.eu/covid19/casedistribution/csv";
        try
            {
            URL rowdata = new URL(url);
            URLConnection data = rowdata.openConnection();
            Scanner scanner = new Scanner(data.getInputStream());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(poland)) {
                    String[] fields = line.split(",");
                    int day = Integer.parseInt(fields[1]);
                    int month = Integer.parseInt(fields[2]);
                    int year = Integer.parseInt(fields[3]);
                    int cases = Integer.parseInt(fields[4]);
                    String country = fields[6];
                    dane.add(new TZachorowania(country,day, month, year, cases));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dane;
    }
}

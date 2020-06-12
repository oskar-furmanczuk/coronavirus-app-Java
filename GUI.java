import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GUI implements ActionListener, ChangeListener {

    public int miesiacUser = 1;
    public int rokUser = 2019;
    public double srednia_ogolna = 0;
    public double srednia_miesieczna = 0;
    public String str_srednia_ogolna = " --- średnia dostępna po kliknięciu RUN!";
    public String str_srednia_miesieczna= " --- średnia dostępna po kliknięciu RUN!";
    JLabel label5 = new JLabel("Średnie dzienne zachorowania dla CAŁEGO OKRESU pandemii: " + str_srednia_ogolna);
    JLabel label6 = new JLabel("Średnie dzienne zachorowania dla WYBRANEGO MIESIĄCA pandemii: " + str_srednia_miesieczna);

    public GUI() {

        JFrame f = new JFrame();

        JButton b = new JButton("RUN!");
        b.addActionListener(this);

        JLabel label1 = new JLabel("KALKULATOR ŚREDNICH ZACHOROWAŃ NA KORONAWIRUSA W POLSCE");
        JLabel label2 = new JLabel("Wybierz miesiac i rok dla wyznaczenia średniej dla danego okresu");
        JLabel label3 = new JLabel("Miesiąc:");
        JLabel label4 = new JLabel("Rok:");

        final int FPS_MIN = 1;
        final int FPS_MAX = 12;
        final int FPS_INIT = 1;
        JSlider wybierzMiesiac = new JSlider(JSlider.HORIZONTAL,
                FPS_MIN, FPS_MAX, FPS_INIT);
        wybierzMiesiac.addChangeListener(this);

        JRadioButton button2019 = new JRadioButton("2019");
        button2019.setMnemonic(KeyEvent.VK_B);
        button2019.setActionCommand("2019");
        button2019.setSelected(true);

        JRadioButton button2020 = new JRadioButton("2020");
        button2020.setMnemonic(KeyEvent.VK_C);
        button2020.setActionCommand("2020");

        JRadioButton button2021 = new JRadioButton("2021");
        button2021.setMnemonic(KeyEvent.VK_D);
        button2021.setActionCommand("2021");

        ButtonGroup group = new ButtonGroup();
        group.add(button2019);
        group.add(button2020);
        group.add(button2021);


        button2019.addActionListener(this);
        button2020.addActionListener(this);
        button2021.addActionListener(this);


        wybierzMiesiac.setMajorTickSpacing(1);
        wybierzMiesiac.setMinorTickSpacing(1);
        wybierzMiesiac.setPaintTicks(true);
        wybierzMiesiac.setPaintLabels(true);


        b.setBounds(700, 380, 200, 100);
        button2019.setBounds(1085, 150, 200, 100);
        button2020.setBounds(1085, 225, 200, 100);
        button2021.setBounds(1085, 300, 200, 100);
        label1.setBounds(600, 10, 600, 150);
        label2.setBounds(615, 30, 600, 150);
        label3.setBounds(375, 70, 600, 150);
        label4.setBounds(1100, 70, 600, 150);
        label5.setBounds(550, 500, 900, 150);
        label6.setBounds(530, 575, 900, 150);
        wybierzMiesiac.setBounds(100, 150, 600, 100);

        f.add(b);//adding button in JFrame
        f.add(wybierzMiesiac);
        f.add(button2019);
        f.add(button2020);
        f.add(button2021);
        f.add(label1);
        f.add(label2);
        f.add(label3);
        f.add(label4);
        f.add(label5);
        f.add(label6);

        f.setSize(1600, 800);
        f.setLayout(null);
        f.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("RUN!"))
        {
            List<String> list = runProgram();
            str_srednia_ogolna = list.get(0);
            str_srednia_miesieczna = list.get(1);
            label5.setText("Średnie dzienne zachorowania dla CAŁEGO OKRESU pandemii: " + list.get(0));
            label6.setText("Średnie dzienne zachorowania dla WYBRANEGO MIESIĄCA pandemii: " + list.get(1));
        }


        else {
            rokUser = Integer.parseInt(command);
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        miesiacUser = source.getValue();
    }

    public List<String> runProgram(){
        List<String> srednie = new ArrayList<>();
        List<TZachorowania> dane = TBufor.przeczytajCSV();
        TPandemia tPandemia = new TPandemia();
        TPandemia tPandemiaMiesieczna = new TPandemia();
        int iloscZachorowan = 0;
        int iloscZachorowanMiesiac = 0;
        for (TZachorowania dzienne_dane: dane) {

            int month = dzienne_dane.getMonth();
            int day = dzienne_dane.getDay();
            int year = dzienne_dane.getYear();
            int cases = dzienne_dane.getCases();
            iloscZachorowan += cases;
            tPandemia.add(dzienne_dane);
            if (miesiacUser == month && rokUser == year) {
                iloscZachorowanMiesiac += cases;
                tPandemiaMiesieczna.add(dzienne_dane);
            }
        }
        int iloscDni = tPandemia.getLiczbaCzynnosci();
        srednia_ogolna = tPandemia.sredniaLiczbaZachorowan(iloscZachorowan, iloscDni);
        srednie.add(String.valueOf(srednia_ogolna)) ;
        int iloscDniMiesiac = tPandemiaMiesieczna.getLiczbaCzynnosci();

        try{
            srednia_miesieczna = tPandemia.sredniaLiczbaZachorowan(iloscZachorowanMiesiac, iloscDniMiesiac);
        } catch (Exception exception) {
            srednia_miesieczna = -1;

        }
        if (iloscDniMiesiac == 0 ){
            srednie.add(" --- brak danych dla wybranego miesiąca") ;
        }
        else{srednie.add(String.valueOf(srednia_miesieczna)) ;}

        return (srednie);
    }
}

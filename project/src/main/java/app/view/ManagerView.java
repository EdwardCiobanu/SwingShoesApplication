package app.view;

import app.model.Magazin;
import app.model.TipIncaltaminte;
import app.presenter.ManagerPresenter;
import app.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManagerView implements IManager{
    private JTable magazineTable;
    private JPanel panel1;
    private JTextField tfProducatorFiltrare;
    private JButton filtreazaDupaProducatorButton;
    private JTextField tfDisponibilitateFiltrare;
    private JButton filtreazaDupaDisponibilitateButton;
    private JTextField tfPretFiltrare;
    private JButton filtreazaDupaPretButton;
    private JButton backButton;

    public ManagerView() {
        GUIFrameSinglePointAccess.changePanel(panel1, "Manager Panel");
        ManagerPresenter managerPresenter = new ManagerPresenter(this);
        Object[][] data = new Object[200][7];
        String[] antet = {"ID magazin", "Nume incaltaminte", "Producator", "Pret", "Culoare", "Numar", "Disponibilitate"};
        List<Magazin> magazinList = managerPresenter.vizualizareMagazine();
        if(magazinList != null){
            int k = 0;
            for(int i = 0; i < magazinList.size(); i++){
                List<TipIncaltaminte> tipIncaltaminteList = magazinList.get(i).getTipIncaltaminteList();
                for(int j = 0; j < tipIncaltaminteList.size(); j++) {
                    data[k][0] = magazinList.get(i).getIdMagazin();
                    data[k][1] = tipIncaltaminteList.get(j).getIncaltaminte().getNume();
                    data[k][2] = tipIncaltaminteList.get(j).getIncaltaminte().getProducator();
                    data[k][3] = tipIncaltaminteList.get(j).getIncaltaminte().getPret();
                    data[k][4] = tipIncaltaminteList.get(j).getCuloare();
                    data[k][5] = tipIncaltaminteList.get(j).getNumar();
                    data[k][6] = tipIncaltaminteList.get(j).getDisponibilitate();
                    k++;
                }
            }
        }
        magazineTable.setModel(new DefaultTableModel(data, antet));

        filtreazaDupaProducatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data1 = new Object[200][7];
                String[] antet1 = {"ID magazin", "Nume incaltaminte", "Producator", "Pret", "Culoare", "Numar", "Disponibilitate"};
                List<Magazin> magazinListUpdated = managerPresenter.filtrareDupaProducator(magazinList);
                if(magazinListUpdated != null){
                    int k = 0;
                    for(int i = 0; i < magazinListUpdated.size(); i++){
                        List<TipIncaltaminte> tipIncaltaminteList = magazinListUpdated.get(i).getTipIncaltaminteList();
                        for(int j = 0; j < tipIncaltaminteList.size(); j++) {
                            data1[k][0] = magazinListUpdated.get(i).getIdMagazin();
                            data1[k][1] = tipIncaltaminteList.get(j).getIncaltaminte().getNume();
                            data1[k][2] = tipIncaltaminteList.get(j).getIncaltaminte().getProducator();
                            data1[k][3] = tipIncaltaminteList.get(j).getIncaltaminte().getPret();
                            data1[k][4] = tipIncaltaminteList.get(j).getCuloare();
                            data1[k][5] = tipIncaltaminteList.get(j).getNumar();
                            data1[k][6] = tipIncaltaminteList.get(j).getDisponibilitate();
                            k++;
                        }
                    }
                    magazineTable.setModel(new DefaultTableModel(data1, antet1));
                }
                else{
                    GUIFrameSinglePointAccess.showDialogMessage("Could not find a magazin where an incaltaminte has that producator");
                }
            }
        });
        filtreazaDupaDisponibilitateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data1 = new Object[200][7];
                String[] antet1 = {"ID magazin", "Nume incaltaminte", "Producator", "Pret", "Culoare", "Numar", "Disponibilitate"};
                List<Magazin> magazinListUpdated = managerPresenter.filtrareDupaDisponibilitate(magazinList);
                if(magazinListUpdated != null){
                    int k = 0;
                    for(int i = 0; i < magazinListUpdated.size(); i++){
                        List<TipIncaltaminte> tipIncaltaminteList = magazinListUpdated.get(i).getTipIncaltaminteList();
                        for(int j = 0; j < tipIncaltaminteList.size(); j++) {
                            data1[k][0] = magazinListUpdated.get(i).getIdMagazin();
                            data1[k][1] = tipIncaltaminteList.get(j).getIncaltaminte().getNume();
                            data1[k][2] = tipIncaltaminteList.get(j).getIncaltaminte().getProducator();
                            data1[k][3] = tipIncaltaminteList.get(j).getIncaltaminte().getPret();
                            data1[k][4] = tipIncaltaminteList.get(j).getCuloare();
                            data1[k][5] = tipIncaltaminteList.get(j).getNumar();
                            data1[k][6] = tipIncaltaminteList.get(j).getDisponibilitate();
                            k++;
                        }
                    }
                    magazineTable.setModel(new DefaultTableModel(data1, antet1));
                }
                else{
                    GUIFrameSinglePointAccess.showDialogMessage("Could not find a magazin where an incaltaminte has that disponibilitate");
                }
            }
        });
        filtreazaDupaPretButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data1 = new Object[200][7];
                String[] antet1 = {"ID magazin", "Nume incaltaminte", "Producator", "Pret", "Culoare", "Numar", "Disponibilitate"};
                List<Magazin> magazinListUpdated = managerPresenter.filtrareDupaPret(magazinList);
                if(magazinListUpdated != null){
                    int k = 0;
                    for(int i = 0; i < magazinListUpdated.size(); i++){
                        List<TipIncaltaminte> tipIncaltaminteList = magazinListUpdated.get(i).getTipIncaltaminteList();
                        for(int j = 0; j < tipIncaltaminteList.size(); j++) {
                            data1[k][0] = magazinListUpdated.get(i).getIdMagazin();
                            data1[k][1] = tipIncaltaminteList.get(j).getIncaltaminte().getNume();
                            data1[k][2] = tipIncaltaminteList.get(j).getIncaltaminte().getProducator();
                            data1[k][3] = tipIncaltaminteList.get(j).getIncaltaminte().getPret();
                            data1[k][4] = tipIncaltaminteList.get(j).getCuloare();
                            data1[k][5] = tipIncaltaminteList.get(j).getNumar();
                            data1[k][6] = tipIncaltaminteList.get(j).getDisponibilitate();
                            k++;
                        }
                    }
                    magazineTable.setModel(new DefaultTableModel(data1, antet1));
                }
                else{
                    GUIFrameSinglePointAccess.showDialogMessage("Could not find a magazin where an incaltaminte has that pret");
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
            }
        });
    }

    @Override
    public String getProducatorFiltrare() {
        if(tfProducatorFiltrare.getText().isEmpty())
            return null;
        else return tfProducatorFiltrare.getText();
    }

    @Override
    public String getPretFiltrare() {
        if(tfPretFiltrare.getText().isEmpty())
            return null;
        else return tfPretFiltrare.getText();
    }

    @Override
    public String getDisponibilitateFiltrare() {
        if(tfDisponibilitateFiltrare.getText().isEmpty())
            return null;
        else return tfDisponibilitateFiltrare.getText();
    }
}

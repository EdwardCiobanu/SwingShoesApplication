package app.view;

import app.model.Incaltaminte;
import app.model.Magazin;
import app.model.TipIncaltaminte;
import app.model.Utilizator;
import app.presenter.AngajatPresenter;
import app.service.MagazinService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AngajatView implements IAngajat{
    private JTable incaltaminteTable;
    private JPanel panel1;
    private JTextField tfNume;
    private JTextField tfProducator;
    private JTextField tfPret;
    private JTable tipIncaltaminteTable;
    private JTextField tfCuloare;
    private JTextField tfNumar;
    private JTextField tfDisponibilitate;
    private JButton adaugaIncaltaminteButton;
    private JButton updateIncaltaminteButton;
    private JButton stergeIncaltaminteButton;
    private JButton adaugaTipIncaltaminteButton;
    private JButton updateTipIncaltaminteButton;
    private JButton stergeTipIncaltaminteButton;
    private JTextField tfProducatorFiltrare;
    private JButton filterButtonProducator;
    private JTextField tfDisponibilitateFiltrare;
    private JButton filterButtonDisponibilitate;
    private JTextField tfPretFiltrare;
    private JButton filterButtonPret;
    private JLabel labelMagazinId;
    private JButton adaugaIncaltaminteaInMagazinButton;
    private JButton backButton;
    private JButton stergeIncaltaminteDinMagazinButton;

    private MagazinService magazinService = ServiceSinglePointAccess.getMagazinService();
    List<Incaltaminte> incaltaminteList;
    List<TipIncaltaminte> magazinIncaltaminteList;

    public void setIncaltaminteList(List<Incaltaminte> incaltaminteList) {
        this.incaltaminteList = incaltaminteList;
    }

    public AngajatView(Utilizator utilizator) {


        GUIFrameSinglePointAccess.changePanel(panel1, "Angajat Panel");
        AngajatPresenter angajatPresenter = new AngajatPresenter(this);


        labelMagazinId.setText("Magazin ID:" + utilizator.getIdMagazin());

        Object[][] data = new Object[200][3];
        String[] antet = {"Nume Incaltaminte", "Producator", "Pret"};
        incaltaminteList = angajatPresenter.vizualizareIncaltaminte();

        Collections.sort(incaltaminteList);

        if(incaltaminteList != null){
            for(int i = 0; i < incaltaminteList.size(); i++){
                data[i][0] = incaltaminteList.get(i).getNume();
                data[i][1] = incaltaminteList.get(i).getProducator();
                data[i][2] = incaltaminteList.get(i).getPret();
            }
        }
        incaltaminteTable.setModel(new DefaultTableModel(data, antet));

        Object[][] data1 = new Object[200][6];
        String[] antet1 = {"Id Tip Incaltaminte", "Culoare", "Numar", "Disponibilitate", "Nume Incaltaminte", "Producator"};
        List<TipIncaltaminte> tipIncaltaminteList = angajatPresenter.vizualizareTipIncaltaminte();
        if(tipIncaltaminteList != null){
            for(int i = 0; i < tipIncaltaminteList.size(); i++){
                data1[i][0] = tipIncaltaminteList.get(i).getIdTipIncaltaminte();
                data1[i][1] = tipIncaltaminteList.get(i).getCuloare();
                data1[i][2] = tipIncaltaminteList.get(i).getNumar();
                data1[i][3] = tipIncaltaminteList.get(i).getDisponibilitate();
                data1[i][4] = tipIncaltaminteList.get(i).getIncaltaminte().getNume();
                data1[i][5] = tipIncaltaminteList.get(i).getIncaltaminte().getProducator();
            }
        }
        tipIncaltaminteTable.setModel(new DefaultTableModel(data1, antet1));


        incaltaminteTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;

                int selectedRow = incaltaminteTable.getSelectedRow();
                if (selectedRow >= 0) { // Check if a row is selected
                    tfNume.setText(incaltaminteTable.getValueAt(selectedRow, 0).toString());
                    tfProducator.setText(incaltaminteTable.getValueAt(selectedRow, 1).toString());
                    tfPret.setText(incaltaminteTable.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        tipIncaltaminteTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;

                int selectedRow = tipIncaltaminteTable.getSelectedRow();
                if (selectedRow >= 0) { // Check if a row is selected
                    tfCuloare.setText(tipIncaltaminteTable.getValueAt(selectedRow, 1).toString());
                    tfNumar.setText(tipIncaltaminteTable.getValueAt(selectedRow, 2).toString());
                    tfDisponibilitate.setText(tipIncaltaminteTable.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        adaugaIncaltaminteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Incaltaminte incaltaminte = angajatPresenter.saveIncaltaminte();
                new AngajatView(utilizator);
            }
        });
        updateIncaltaminteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Incaltaminte incaltaminte = angajatPresenter.updateIncaltaminte();
                new AngajatView(utilizator);
            }
        });
        stergeIncaltaminteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Incaltaminte incaltaminte = angajatPresenter.deleteIncaltaminte();
                new AngajatView(utilizator);
            }
        });
        adaugaTipIncaltaminteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipIncaltaminte tipIncaltaminte = angajatPresenter.saveTipIncaltaminte();
                new AngajatView(utilizator);
            }
        });
        updateTipIncaltaminteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipIncaltaminte tipIncaltaminte = angajatPresenter.updateTipIncaltaminte();
                new AngajatView(utilizator);
            }
        });
        stergeTipIncaltaminteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipIncaltaminte tipIncaltaminte = angajatPresenter.deleteTipIncaltaminte();
                new AngajatView(utilizator);
            }
        });

        filterButtonProducator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data = new Object[200][3];
                String[] antet = {"Nume Incaltaminte", "Producator", "Pret"};
                incaltaminteList = angajatPresenter.filtrareDupaProducator();

                if(incaltaminteList != null) {
                    Collections.sort(incaltaminteList);
                    if (incaltaminteList != null) {
                        for (int i = 0; i < incaltaminteList.size(); i++) {
                            data[i][0] = incaltaminteList.get(i).getNume();
                            data[i][1] = incaltaminteList.get(i).getProducator();
                            data[i][2] = incaltaminteList.get(i).getPret();
                        }
                    }
                    incaltaminteTable.setModel(new DefaultTableModel(data, antet));
                }
            }
        });

        filterButtonPret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data = new Object[200][3];
                String[] antet = {"Nume Incaltaminte", "Producator", "Pret"};
                incaltaminteList = angajatPresenter.filtrareDupaPret();

                if(incaltaminteList != null) {
                    Collections.sort(incaltaminteList);
                    if (incaltaminteList != null) {
                        for (int i = 0; i < incaltaminteList.size(); i++) {
                            data[i][0] = incaltaminteList.get(i).getNume();
                            data[i][1] = incaltaminteList.get(i).getProducator();
                            data[i][2] = incaltaminteList.get(i).getPret();
                        }
                    }
                    incaltaminteTable.setModel(new DefaultTableModel(data, antet));
                }
            }
        });

        filterButtonDisponibilitate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data1 = new Object[200][6];
                String[] antet1 = {"Id Tip Incaltaminte", "Culoare", "Numar", "Disponibilitate", "Nume Incaltaminte", "Producator"};
                List<TipIncaltaminte> tipIncaltaminteList = angajatPresenter.filtrareDupaDisponibilitate();
                if(tipIncaltaminteList != null){
                    for(int i = 0; i < tipIncaltaminteList.size(); i++){
                        data1[i][0] = tipIncaltaminteList.get(i).getIdTipIncaltaminte();
                        data1[i][1] = tipIncaltaminteList.get(i).getCuloare();
                        data1[i][2] = tipIncaltaminteList.get(i).getNumar();
                        data1[i][3] = tipIncaltaminteList.get(i).getDisponibilitate();
                        data1[i][4] = tipIncaltaminteList.get(i).getIncaltaminte().getNume();
                        data1[i][5] = tipIncaltaminteList.get(i).getIncaltaminte().getProducator();
                    }
                    tipIncaltaminteTable.setModel(new DefaultTableModel(data1, antet1));
                }

            }
        });
        adaugaIncaltaminteaInMagazinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer magazinID = utilizator.getIdMagazin();
                Magazin magazin = magazinService.findById(magazinID); // Fetch magazin with tipIncaltaminteList eagerly
                if (magazin != null) {
                    List<TipIncaltaminte> magazinIncaltaminteList = new ArrayList<>(magazin.getTipIncaltaminteList());
                    // Create a new list to detach it from Hibernate session
                    List<TipIncaltaminte> updatedList = angajatPresenter.adaugaStocMagazin(magazinIncaltaminteList);
                    magazin.setTipIncaltaminteList(updatedList);
                    Magazin magazinAdaugat = magazinService.update(magazin);
//                    if (magazinAdaugat != null) {
//                        GUIFrameSinglePointAccess.showDialogMessage("Succesfully added magazin");
//                    } else {
//                        GUIFrameSinglePointAccess.showDialogMessage("Error adding magazin");
//                    }
                }
                else{
                    List<TipIncaltaminte> magazinIncaltaminteList = new ArrayList<>();
                    List<TipIncaltaminte> magazinIncaltaminteList1 = angajatPresenter.adaugaStocMagazin(magazinIncaltaminteList);
                    if(magazinIncaltaminteList1 != null) {
                        Magazin magazin1 = new Magazin(utilizator.getIdMagazin(), magazinIncaltaminteList1);
                        Magazin magazinAdaugat = magazinService.update(magazin1);
//                        if (magazinAdaugat != null) {
//                            GUIFrameSinglePointAccess.showDialogMessage("Succesfully added magazin");
//                        } else {
//                            GUIFrameSinglePointAccess.showDialogMessage("Error adding magazin");
//                        }
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
            }
        });
        stergeIncaltaminteDinMagazinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer magazinID = utilizator.getIdMagazin();
                Magazin magazin = magazinService.findById(magazinID); // Fetch magazin with tipIncaltaminteList eagerly
                if (magazin != null) {
                    List<TipIncaltaminte> magazinIncaltaminteList = new ArrayList<>(magazin.getTipIncaltaminteList());
                    // Create a new list to detach it from Hibernate session
                    List<TipIncaltaminte> updatedList = angajatPresenter.stergeStocMagazin(magazinIncaltaminteList);
                    magazin.setTipIncaltaminteList(updatedList);
                    Magazin magazinAdaugat = magazinService.update(magazin);
                }
            }
        });
    }

    @Override
    public String getNumeIncaltaminte() {
        if(tfNume.getText().isEmpty())
            return null;
        else return tfNume.getText();
    }

    @Override
    public String getProducatorIncaltaminte() {
        if(tfProducator.getText().isEmpty())
            return null;
        else return tfProducator.getText();
    }

    @Override
    public String getPretIncaltaminte() {
        if(tfPret.getText().isEmpty())
            return null;
        else return tfPret.getText();
    }

    @Override
    public String getCuloareTipIncaltaminte() {
        if(tfCuloare.getText().isEmpty())
            return null;
        else return tfCuloare.getText();
    }

    @Override
    public String getNumarTipIncaltaminte() {
        if(tfNumar.getText().isEmpty())
            return null;
        else return tfNumar.getText();
    }

    @Override
    public String getDisponibilitateTipIncaltaminte() {
        if(tfDisponibilitate.getText().isEmpty())
            return null;
        else return tfDisponibilitate.getText();
    }

    @Override
    public String getSelectedIncaltaminteNume() {
        int selectedRow = -1;
        selectedRow = incaltaminteTable.getSelectedRow();
        if(selectedRow != -1)
            return incaltaminteTable.getValueAt(selectedRow, 0).toString();
        else return null;
    }

    @Override
    public String getSelectedTipIncaltaminteId() {
        int selectedRow = -1;
        selectedRow = tipIncaltaminteTable.getSelectedRow();
        if(selectedRow != -1)
            return tipIncaltaminteTable.getValueAt(selectedRow, 0).toString();
        else return null;
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

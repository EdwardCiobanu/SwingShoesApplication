package app.view;

import app.model.Utilizator;
import app.presenter.AdministratorPresenter;
import app.single_point_access.GUIFrameSinglePointAccess;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdministratorView implements IAdministrator{
    private JTable utilizatoriTable;
    private JPanel panel1;
    private JTextField tfNume;
    private JTextField tfEmail;
    private JTextField tfPassword;
    private JTextField tfIdMagazin;
    private JButton buttonAdd;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JRadioButton angajatRadioButton;
    private JRadioButton managerRadioButton;
    private JRadioButton administratorRadioButton;
    private JButton buttonBack;

    AdministratorPresenter administratorPresenter;


    public AdministratorView() {
        GUIFrameSinglePointAccess.changePanel(panel1, "Administrator Panel");
        AdministratorPresenter administratorPresenter = new AdministratorPresenter(this);
        ButtonGroup G = new ButtonGroup();
        G.add(angajatRadioButton);
        G.add(managerRadioButton);
        G.add(administratorRadioButton);
        Object[][] data = new Object[200][5];
        String[] antet = {"Nume Utilizator", "Tip Utilizator", "Email", "Parola", "Id magazin"};
        List<Utilizator> utilizators = administratorPresenter.vizualizareUtilizatori();
        if(utilizators != null){
            for(int i = 0; i < utilizators.size(); i++){
                data[i][0] = utilizators.get(i).getNume();
                data[i][1] = utilizators.get(i).getUserType();
                data[i][2] = utilizators.get(i).getEmail();
                data[i][3] = utilizators.get(i).getPassword();
                data[i][4] = utilizators.get(i).getIdMagazin();
            }
        }
        utilizatoriTable.setModel(new DefaultTableModel(data, antet));

        utilizatoriTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;

                int selectedRow = utilizatoriTable.getSelectedRow();
                if (selectedRow >= 0) { // Check if a row is selected
                    tfNume.setText(utilizatoriTable.getValueAt(selectedRow, 0).toString());
                    tfEmail.setText(utilizatoriTable.getValueAt(selectedRow, 2).toString());
                    tfPassword.setText(utilizatoriTable.getValueAt(selectedRow, 3).toString());
                    tfIdMagazin.setText(utilizatoriTable.getValueAt(selectedRow, 4).toString());

                    String userType = utilizatoriTable.getValueAt(selectedRow, 1).toString();
                    if(userType.equals("Angajat")) angajatRadioButton.setSelected(true);
                    else if(userType.equals("Manager")) managerRadioButton.setSelected(true);
                    else if(userType.equals("Administrator")) administratorRadioButton.setSelected(true);
                }
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilizator utilizator = administratorPresenter.save();
                new AdministratorView();
            }
        });

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilizator utilizator = administratorPresenter.update();
                new AdministratorView();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilizator utilizator = administratorPresenter.delete();
                new AdministratorView();
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
            }
        });
    }

    @Override
    public String getNumeUtilizator() {
        if(tfNume.getText().isEmpty())
            return null;
        else return tfNume.getText();
    }

    @Override
    public String getTipUtilizator() {
        if(angajatRadioButton.isSelected())
            return "Angajat";
        else if(managerRadioButton.isSelected())
            return "Manager";
        else if(administratorRadioButton.isSelected())
            return "Administrator";
        return null;
    }

    @Override
    public String getEmailUtilizator() {
        if(tfEmail.getText().isEmpty())
            return null;
        else return tfEmail.getText();
    }

    @Override
    public String getPasswordUtilizator() {
        if(tfPassword.getText().isEmpty())
            return null;
        else return tfPassword.getText();
    }

    @Override
    public String getIdMagazin() {
        if(tfIdMagazin.getText().isEmpty())
            return null;
        else return tfIdMagazin.getText();
    }

    @Override
    public String getSelectedUserEmail() {
        int selectedRow = -1;
        selectedRow = utilizatoriTable.getSelectedRow();
        if(selectedRow != -1)
            return utilizatoriTable.getValueAt(selectedRow, 2).toString();
        else return null;
    }
}

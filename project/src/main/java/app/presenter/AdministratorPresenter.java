package app.presenter;

import app.model.Utilizator;
import app.service.UtilizatorService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.IAdministrator;
import app.view.ILogin;

import java.util.List;

public class AdministratorPresenter {
    private IAdministrator iAdministrator;
    private UtilizatorService utilizatorService = ServiceSinglePointAccess.getUtilizatorService();
    public AdministratorPresenter(IAdministrator iAdministrator){
        this.iAdministrator = iAdministrator;
    }

    public Utilizator save(){
        String numeUtilizator = iAdministrator.getNumeUtilizator();
        String tipUtilizator = iAdministrator.getTipUtilizator();
        String emailUtilizator = iAdministrator.getEmailUtilizator();
        String password = iAdministrator.getPasswordUtilizator();
        String idMagazin = iAdministrator.getIdMagazin();

        if(numeUtilizator == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete nume field");
            return null;
        }
        if(tipUtilizator == null){
            GUIFrameSinglePointAccess.showDialogMessage("Check a tip utilizator button");
            return null;
        }
        else if(emailUtilizator == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete email field");
            return null;
        }
        else if(password == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete password field");
            return null;
        }
        else if(idMagazin == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete id magazin field");
            return null;
        }

        else{
            Integer idMagazin1 = Integer.valueOf(idMagazin);
            Utilizator utilizator1 = utilizatorService.findUtilizatorByEmail(emailUtilizator);

            if(utilizator1 == null) {
                Utilizator utilizator = new Utilizator(numeUtilizator, tipUtilizator, emailUtilizator, password, idMagazin1);
                Utilizator utilizatorAdaugat = utilizatorService.save(utilizator);
                if (utilizatorAdaugat != null) {
                    GUIFrameSinglePointAccess.showDialogMessage("Succesfully added");
                    return utilizatorAdaugat;
                }
            }
            GUIFrameSinglePointAccess.showDialogMessage("Already existing email");
            return null;

        }

    }

    public Utilizator update(){

        String email = iAdministrator.getSelectedUserEmail();
        if(email == null){
            GUIFrameSinglePointAccess.showDialogMessage("Select an user");
            return null;
        }
        else{
            Utilizator utilizatorCautat = utilizatorService.findUtilizatorByEmail(email);

            String numeUtilizator = iAdministrator.getNumeUtilizator();
            String tipUtilizator = iAdministrator.getTipUtilizator();
            String password = iAdministrator.getPasswordUtilizator();
            String idMagazin = iAdministrator.getIdMagazin();
            String emailUtilizator = iAdministrator.getEmailUtilizator();

            if(numeUtilizator == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete nume field");
                return null;
            }
            else if(tipUtilizator == null){
                GUIFrameSinglePointAccess.showDialogMessage("Check a tip utilizator button");
                return null;
            }
            else if(emailUtilizator == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete email field");
                return null;
            }
            else if(password == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete password field");
                return null;
            }
            else if(idMagazin == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete id magazin field");
                return null;
            }

            Integer idMagazin1 = Integer.valueOf(idMagazin);

            Utilizator utilizator1 = utilizatorService.findUtilizatorByEmail(emailUtilizator);

            if(utilizator1 == null || utilizator1.getIdUtilizator() == utilizatorCautat.getIdUtilizator()) {
                utilizatorCautat.setNume(numeUtilizator);
                utilizatorCautat.setUserType(tipUtilizator);
                utilizatorCautat.setEmail(emailUtilizator);
                utilizatorCautat.setPassword(password);
                utilizatorCautat.setIdMagazin(idMagazin1);
                utilizatorService.update(utilizatorCautat);
                GUIFrameSinglePointAccess.showDialogMessage("Succesfully updated");
                return utilizatorCautat;
            }
            GUIFrameSinglePointAccess.showDialogMessage("Already existing email");
            return null;
        }
    }

    public Utilizator delete(){
        String numeUtilizator = iAdministrator.getNumeUtilizator();
        String tipUtilizator = iAdministrator.getTipUtilizator();
        String emailUtilizator = iAdministrator.getEmailUtilizator();
        String password = iAdministrator.getPasswordUtilizator();
        String idMagazin = iAdministrator.getIdMagazin();

        if(numeUtilizator == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete nume field");
            return null;
        }
        if(tipUtilizator == null){
            GUIFrameSinglePointAccess.showDialogMessage("Check a tip utilizator button");
            return null;
        }
        else if(emailUtilizator == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete email field");
            return null;
        }
        else if(password == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete password field");
            return null;
        }
        else if(idMagazin == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete id magazin field");
            return null;
        }
        else{
            String email = iAdministrator.getSelectedUserEmail();
            if(email == null){
                GUIFrameSinglePointAccess.showDialogMessage("Error deleting utilizator");
                return null;
            }
            else {
                Utilizator utilizator = utilizatorService.findUtilizatorByEmail(emailUtilizator);
                utilizatorService.delete(utilizator);
                GUIFrameSinglePointAccess.showDialogMessage("Succesfully deleted");
                return utilizator;
            }
        }

    }

    public List<Utilizator> vizualizareUtilizatori(){
        List<Utilizator> utilizators;
        utilizators = utilizatorService.findAll();
        return utilizators;
    }
}

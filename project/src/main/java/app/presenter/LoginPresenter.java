package app.presenter;

import app.model.Utilizator;
import app.service.UtilizatorService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.ILogin;

public class LoginPresenter {
    private ILogin iLogin;
    private UtilizatorService utilizatorService = ServiceSinglePointAccess.getUtilizatorService();
    public LoginPresenter(ILogin iLogin){
        this.iLogin = iLogin;
    }

    public Utilizator login(){
        String emailUtilizator = iLogin.getEmailUtilizator();
        String password = iLogin.getPasswordUtilizator();

        if(emailUtilizator.isEmpty()){
            GUIFrameSinglePointAccess.showDialogMessage("Complete email field");
            return null;
        }
        else if(password.isEmpty()){
            GUIFrameSinglePointAccess.showDialogMessage("Complete password field");
            return null;
        }
        else{
            Utilizator utilizator = utilizatorService.login(emailUtilizator, password);
            if (utilizator != null) {
                GUIFrameSinglePointAccess.showDialogMessage("Succesfully login");
                return utilizator;
            }
            else {
                GUIFrameSinglePointAccess.showDialogMessage("Invalid username or password");
                return null;
            }
        }

    }
}

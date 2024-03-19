package app.presenter;

import app.model.Incaltaminte;
import app.model.Magazin;
import app.model.TipIncaltaminte;
import app.model.Utilizator;
import app.service.MagazinService;
import app.service.UtilizatorService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.IAdministrator;
import app.view.IManager;

import java.util.ArrayList;
import java.util.List;

public class ManagerPresenter {

    private IManager iManager;
    private MagazinService magazinService = ServiceSinglePointAccess.getMagazinService();
    public ManagerPresenter(IManager iManager){
        this.iManager = iManager;
    }


    public List<Magazin> vizualizareMagazine(){
        List<Magazin> magazinList;
        magazinList = magazinService.findAll();
        return magazinList;
    }

    public List<Magazin> filtrareDupaProducator(List<Magazin> magazinList) {
        String producatorFiltrare = iManager.getProducatorFiltrare();

        if (producatorFiltrare == null || producatorFiltrare.isEmpty()) {
            GUIFrameSinglePointAccess.showDialogMessage("Complete producator filtrare field");
            return null;
        }

        List<Magazin> magazinListUpdated = new ArrayList<>();
        if (magazinList != null) {
            for (Magazin magazin : magazinList) {
                boolean found = false;
                List<TipIncaltaminte> tipIncaltaminteList = magazin.getTipIncaltaminteList();
                for (TipIncaltaminte tipIncaltaminte : tipIncaltaminteList) {
                    if (tipIncaltaminte.getIncaltaminte().getProducator().equals(producatorFiltrare)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    magazinListUpdated.add(magazin);
                }
            }
        }

        if (magazinListUpdated.isEmpty()) {
            return null;
        } else {
            return magazinListUpdated;
        }
    }

    public List<Magazin> filtrareDupaDisponibilitate(List<Magazin> magazinList) {
        String disponibilitateFiltrare = iManager.getDisponibilitateFiltrare();

        if (disponibilitateFiltrare == null || disponibilitateFiltrare.isEmpty()) {
            GUIFrameSinglePointAccess.showDialogMessage("Complete disponibilitate filtrare field");
            return null;
        }

        Integer disponibilitateFiltrare1 = Integer.valueOf(disponibilitateFiltrare);
        List<Magazin> magazinListUpdated = new ArrayList<>();
        if (magazinList != null) {
            for (Magazin magazin : magazinList) {
                boolean found = false;
                List<TipIncaltaminte> tipIncaltaminteList = magazin.getTipIncaltaminteList();
                for (TipIncaltaminte tipIncaltaminte : tipIncaltaminteList) {
                    if (tipIncaltaminte.getDisponibilitate() == disponibilitateFiltrare1) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    magazinListUpdated.add(magazin);
                }
            }
        }

        if (magazinListUpdated.isEmpty()) {
            return null;
        } else {
            return magazinListUpdated;
        }
    }


    public List<Magazin> filtrareDupaPret(List<Magazin> magazinList) {
        String pretFiltrare = iManager.getPretFiltrare();

        if (pretFiltrare == null || pretFiltrare.isEmpty()) {
            GUIFrameSinglePointAccess.showDialogMessage("Complete pret filtrare field");
            return null;
        }

        Integer pretFiltrare1 = Integer.valueOf(pretFiltrare);
        List<Magazin> magazinListUpdated = new ArrayList<>();
        if (magazinList != null) {
            for (Magazin magazin : magazinList) {
                boolean found = false;
                List<TipIncaltaminte> tipIncaltaminteList = magazin.getTipIncaltaminteList();
                for (TipIncaltaminte tipIncaltaminte : tipIncaltaminteList) {
                    if (tipIncaltaminte.getIncaltaminte().getPret().equals(pretFiltrare1)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    magazinListUpdated.add(magazin);
                }
            }
        }

        if (magazinListUpdated.isEmpty()) {
            return null;
        } else {
            return magazinListUpdated;
        }
    }

}

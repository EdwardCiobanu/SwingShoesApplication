package app.presenter;

import app.model.Incaltaminte;
import app.model.TipIncaltaminte;
import app.model.Utilizator;
import app.service.IncaltaminteService;
import app.service.TipIncaltaminteService;
import app.service.UtilizatorService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.IAdministrator;
import app.view.IAngajat;
import app.view.ILogin;

import java.util.Iterator;
import java.util.List;

public class AngajatPresenter {
    private IAngajat iAngajat;
    private IncaltaminteService incaltaminteService = ServiceSinglePointAccess.getIncaltaminteService();
    private TipIncaltaminteService tipIncaltaminteService = ServiceSinglePointAccess.getTipIncaltaminteService();

    public AngajatPresenter(IAngajat iAngajat){
        this.iAngajat = iAngajat;
    }

    public Incaltaminte saveIncaltaminte(){
        String numeIncaltaminte = iAngajat.getNumeIncaltaminte();
        String producator = iAngajat.getProducatorIncaltaminte();
        String pret = iAngajat.getPretIncaltaminte();

        if(numeIncaltaminte == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete nume field");
            return null;
        }
        if(producator == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete producator field");
            return null;
        }
        else if(pret == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete pret field");
            return null;
        }

        else{

            Incaltaminte incaltaminte1 = incaltaminteService.findIncaltaminteByNume(numeIncaltaminte);

            if(incaltaminte1 == null) {
                Integer pret1 = Integer.valueOf(pret);
                Incaltaminte incaltaminte = new Incaltaminte(numeIncaltaminte, producator, pret1);
                Incaltaminte incaltaminteAdaugata = incaltaminteService.save(incaltaminte);
                if (incaltaminteAdaugata != null) {
                    GUIFrameSinglePointAccess.showDialogMessage("Succesfully added");
                    return incaltaminteAdaugata;
                }
            }
            GUIFrameSinglePointAccess.showDialogMessage("Already existing nume");
            return null;

        }

    }

    public Incaltaminte updateIncaltaminte(){

        String nume = iAngajat.getSelectedIncaltaminteNume();
        if(nume == null){
            GUIFrameSinglePointAccess.showDialogMessage("Select an incaltaminte");
            return null;
        }
        else{
            Incaltaminte incaltaminteCautata = incaltaminteService.findIncaltaminteByNume(nume);

            String numeIncaltaminte = iAngajat.getNumeIncaltaminte();
            String producator = iAngajat.getProducatorIncaltaminte();
            String pret = iAngajat.getPretIncaltaminte();

            if(numeIncaltaminte == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete nume field");
                return null;
            }
            else if(producator == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete producator field");
                return null;
            }
            else if(pret == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete pret field");
                return null;
            }

            Integer pret1 = Integer.valueOf(pret);

            Incaltaminte incaltaminte1 = incaltaminteService.findIncaltaminteByNume(numeIncaltaminte);

            if(incaltaminte1 == null || incaltaminte1.getIdIncaltaminte() == incaltaminteCautata.getIdIncaltaminte()) {
                incaltaminteCautata.setNume(numeIncaltaminte);
                incaltaminteCautata.setProducator(producator);
                incaltaminteCautata.setPret(pret1);
                GUIFrameSinglePointAccess.showDialogMessage("Succesfully updated");
                incaltaminteService.update(incaltaminteCautata);
                return incaltaminteCautata;
            }
            GUIFrameSinglePointAccess.showDialogMessage("Already existing nume");
            return null;
        }
    }

    public Incaltaminte deleteIncaltaminte(){
        String numeIncaltaminte = iAngajat.getNumeIncaltaminte();
//        String producator = iAngajat.getProducatorIncaltaminte();
//        String pret = iAngajat.getPretIncaltaminte();

        if(numeIncaltaminte == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete nume field");
            return null;
        }
//        if(producator == null){
//            GUIFrameSinglePointAccess.showDialogMessage("Complete producator field");
//            return null;
//        }
//        else if(pret == null){
//            GUIFrameSinglePointAccess.showDialogMessage("Complete pret field");
//            return null;
//        }

        else{
            String nume = iAngajat.getSelectedIncaltaminteNume();
            if(nume == null){
                GUIFrameSinglePointAccess.showDialogMessage("Error deleting incaltaminte");
                return null;
            }
            else {
                Incaltaminte incaltaminte = incaltaminteService.findIncaltaminteByNume(nume);
                incaltaminteService.delete(incaltaminte);
                GUIFrameSinglePointAccess.showDialogMessage("Succesfully deleted");
                return incaltaminte;
            }
        }

    }

    public List<Incaltaminte> vizualizareIncaltaminte(){
        List<Incaltaminte> incaltaminteList;
        incaltaminteList = incaltaminteService.findAll();
        return incaltaminteList;
    }

    public TipIncaltaminte saveTipIncaltaminte(){
        String numeIncaltaminte = iAngajat.getSelectedIncaltaminteNume();
        String culoare = iAngajat.getCuloareTipIncaltaminte();
        String numar = iAngajat.getNumarTipIncaltaminte();
        String disponibilitate = iAngajat.getDisponibilitateTipIncaltaminte();

        if(numeIncaltaminte == null){
            GUIFrameSinglePointAccess.showDialogMessage("Select an incaltaminte");
            return null;
        }
        if(culoare == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete culoare field");
            return null;
        }
        if(numar == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete numar field");
            return null;
        }
        else if(disponibilitate == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete disponibilitate field");
            return null;
        }

        else{
            Incaltaminte incaltaminte = incaltaminteService.findIncaltaminteByNume(numeIncaltaminte);
            Integer numar1 = Integer.valueOf(numar);
            Integer disponibilitate1 = Integer.valueOf(disponibilitate);
            TipIncaltaminte tipIncaltaminte = new TipIncaltaminte(incaltaminte, culoare, numar1, disponibilitate1);
            TipIncaltaminte tipIncaltaminteAdaugata = tipIncaltaminteService.save(tipIncaltaminte);
            if (tipIncaltaminteAdaugata != null) {
                GUIFrameSinglePointAccess.showDialogMessage("Succesfully added");
                return tipIncaltaminteAdaugata;
            }
        }
        GUIFrameSinglePointAccess.showDialogMessage("Error adding tip incaltaminte");
        return null;
    }


    public TipIncaltaminte updateTipIncaltaminte(){

        String id = iAngajat.getSelectedTipIncaltaminteId();
        if(id == null){
            GUIFrameSinglePointAccess.showDialogMessage("Select a tip incaltaminte");
            return null;
        }
        else{
            Integer id1 = Integer.valueOf(id);
            TipIncaltaminte tipIncaltaminteCautata = tipIncaltaminteService.findById(id1);

            String culoare = iAngajat.getCuloareTipIncaltaminte();
            String numar = iAngajat.getNumarTipIncaltaminte();
            String disponibilitate = iAngajat.getDisponibilitateTipIncaltaminte();

            if(culoare == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete culoare field");
                return null;
            }
            else if(numar == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete numar field");
                return null;
            }
            else if(disponibilitate == null){
                GUIFrameSinglePointAccess.showDialogMessage("Complete disponibilitate field");
                return null;
            }

            Integer numar1 = Integer.valueOf(numar);
            Integer disponibilitate1 = Integer.valueOf(disponibilitate);



            if(tipIncaltaminteCautata != null) {
                tipIncaltaminteCautata.setCuloare(culoare);
                tipIncaltaminteCautata.setNumar(numar1);
                tipIncaltaminteCautata.setDisponibilitate(disponibilitate1);
                GUIFrameSinglePointAccess.showDialogMessage("Succesfully updated");
                tipIncaltaminteService.update(tipIncaltaminteCautata);
                return tipIncaltaminteCautata;
            }
            GUIFrameSinglePointAccess.showDialogMessage("Error updating tip incaltaminte");
            return null;
        }
    }

    public TipIncaltaminte deleteTipIncaltaminte(){
        String id = iAngajat.getSelectedTipIncaltaminteId();


        if(id == null){
            GUIFrameSinglePointAccess.showDialogMessage("Select a tip incaltaminte");
            return null;
        }
        else {
            Integer id1 = Integer.valueOf(id);
            TipIncaltaminte tipIncaltaminte = tipIncaltaminteService.findById(id1);
            tipIncaltaminteService.delete(tipIncaltaminte);
            GUIFrameSinglePointAccess.showDialogMessage("Succesfully deleted");
            return tipIncaltaminte;
        }

    }

    public List<TipIncaltaminte> vizualizareTipIncaltaminte(){
        List<TipIncaltaminte> tipIncaltaminteList;
        tipIncaltaminteList = tipIncaltaminteService.findAll();
        return tipIncaltaminteList;
    }

    public List<Incaltaminte> filtrareDupaProducator(){
        String producatorFiltrare = iAngajat.getProducatorFiltrare();

        if(producatorFiltrare == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete producator filtrare field");
            return null;
        }

        else{
            List<Incaltaminte> incaltaminteList = incaltaminteService.findAllIncaltaminteByProducator(producatorFiltrare);
            return incaltaminteList;
        }

    }

    public List<Incaltaminte> filtrareDupaPret(){
        String pretFiltrare = iAngajat.getPretFiltrare();

        if(pretFiltrare == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete pret filtrare field");
            return null;
        }

        else{
            Integer pretFiltrare1 = Integer.valueOf(pretFiltrare);
            List<Incaltaminte> incaltaminteList = incaltaminteService.findAllIncaltaminteByPret(pretFiltrare1);
            return incaltaminteList;
        }
    }

    public List<TipIncaltaminte> filtrareDupaDisponibilitate(){
        String disponibilitateFiltrare = iAngajat.getDisponibilitateFiltrare();

        if(disponibilitateFiltrare == null){
            GUIFrameSinglePointAccess.showDialogMessage("Complete disponibilitate filtrare field");
            return null;
        }

        else{
            Integer disponibilitateFiltrare1 = Integer.valueOf(disponibilitateFiltrare);
            List<TipIncaltaminte> tipIncaltaminteList = tipIncaltaminteService.findAllTipIncaltaminteByDisponibilitate(disponibilitateFiltrare1);
            return tipIncaltaminteList;
        }
    }

    public List<TipIncaltaminte> adaugaStocMagazin(List<TipIncaltaminte> magazinIncaltaminteList){
        String id = iAngajat.getSelectedTipIncaltaminteId();


        if(id == null){
            GUIFrameSinglePointAccess.showDialogMessage("Select a tip incaltaminte");
            return null;
        }
        else {
            Integer id1 = Integer.valueOf(id);
            TipIncaltaminte tipIncaltaminte = tipIncaltaminteService.findById(id1);
            boolean alreadyExists = false;
            for (TipIncaltaminte existingIncaltaminte : magazinIncaltaminteList) {
                if (existingIncaltaminte.getIdTipIncaltaminte().equals(id1)) {
                    alreadyExists = true;
                    break;
                }
            }
            if (!alreadyExists) {
                magazinIncaltaminteList.add(tipIncaltaminte);
                GUIFrameSinglePointAccess.showDialogMessage("Successfully added incaltaminte to magazin");
            } else {
                GUIFrameSinglePointAccess.showDialogMessage("Already existing incaltaminte in magazin");
            }

            return magazinIncaltaminteList;
        }

    }

    public List<TipIncaltaminte> stergeStocMagazin(List<TipIncaltaminte> magazinIncaltaminteList){
        String id = iAngajat.getSelectedTipIncaltaminteId();

        if(id == null){
            GUIFrameSinglePointAccess.showDialogMessage("Select a tip incaltaminte");
            return null;
        }
        else {
            Integer id1 = Integer.valueOf(id);
            TipIncaltaminte tipIncaltaminte = tipIncaltaminteService.findById(id1);

            boolean found = false;
            for (Iterator<TipIncaltaminte> iterator = magazinIncaltaminteList.iterator(); iterator.hasNext(); ) {
                TipIncaltaminte existingIncaltaminte = iterator.next();
                if (existingIncaltaminte.getIdTipIncaltaminte().equals(id1)) {
                    iterator.remove(); // Remove the matching TipIncaltaminte
                    found = true;
                    break;
                }
            }

            if (found) {
                GUIFrameSinglePointAccess.showDialogMessage("Successfully removed incaltaminte from magazin");
            } else {
                GUIFrameSinglePointAccess.showDialogMessage("Tip incaltaminte not found in magazin");
            }

            return magazinIncaltaminteList;
        }

    }
}

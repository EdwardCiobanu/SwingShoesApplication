package app.single_point_access;

import app.service.*;
import app.service.implementation.*;

public class ServiceSinglePointAccess {

    private static UtilizatorService utilizatorService;
    private static IncaltaminteService incaltaminteService;
    private static TipIncaltaminteService tipIncaltaminteService;
    private static MagazinService magazinService;

    static {
        utilizatorService = new UtilizatorServiceImpl();
        incaltaminteService = new IncaltaminteServiceImpl();
        tipIncaltaminteService = new TipIncaltaminteServiceImpl();
        magazinService = new MagazinServiceImpl();
    }

    public static UtilizatorService getUtilizatorService() { return utilizatorService; }
    public static IncaltaminteService getIncaltaminteService() { return incaltaminteService; }
    public static TipIncaltaminteService getTipIncaltaminteService() { return tipIncaltaminteService; }
    public static MagazinService getMagazinService() { return magazinService; }

}

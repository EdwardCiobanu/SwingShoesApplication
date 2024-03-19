package app.single_point_access;

import app.repository.*;
import app.repository.implemetation.*;

public class RepositorySinglePointAccess {


    private static UtilizatorRepository utilizatorRepository;
    private static IncaltaminteRepository incaltaminteRepository;
    private static TipIncaltaminteRepository tipIncaltaminteRepository;
    private static MagazinRepository magazinRepository;

    static {
        utilizatorRepository = new UtilizatorRepositoryImpl();
        incaltaminteRepository = new IncaltaminteRepositoryImpl();
        tipIncaltaminteRepository = new TipIncaltaminteRepositoryImpl();
        magazinRepository = new MagazinRepositoryImpl();
    }

    public static UtilizatorRepository getUtilizatorRepository() { return utilizatorRepository; }
    public static IncaltaminteRepository getIncaltaminteRepository() { return incaltaminteRepository; }
    public static TipIncaltaminteRepository getTipIncaltaminteRepository() { return tipIncaltaminteRepository; }
    public static MagazinRepository getMagazinRepository() { return magazinRepository; }

}

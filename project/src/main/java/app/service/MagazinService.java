package app.service;

import app.model.Incaltaminte;
import app.model.Magazin;
import app.model.TipIncaltaminte;
import app.model.Utilizator;

import java.util.List;

public interface MagazinService {

    Magazin save(Magazin magazin);

    Magazin update(Magazin magazin);

    List<Magazin> findAll();

    Magazin findById(Integer id);

    boolean delete(Magazin magazin);

}

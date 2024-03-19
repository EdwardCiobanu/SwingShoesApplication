package app.service;

import app.model.Incaltaminte;
import app.model.Utilizator;

import java.util.List;

public interface IncaltaminteService {

    Incaltaminte save(Incaltaminte incaltaminte);

    Incaltaminte update(Incaltaminte incaltaminte);

    List<Incaltaminte> findAll();
    List<Incaltaminte> findAllIncaltaminteByProducator(String producator);
    List<Incaltaminte> findAllIncaltaminteByPret(Integer pret);
    Incaltaminte findById(Integer id);

    Incaltaminte findIncaltaminteByProducator(String producator);
    Incaltaminte findIncaltaminteByNume(String nume);

    boolean delete(Incaltaminte incaltaminte);

}

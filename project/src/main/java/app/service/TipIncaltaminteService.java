package app.service;

import app.model.Incaltaminte;
import app.model.TipIncaltaminte;
import app.model.Utilizator;

import java.util.List;

public interface TipIncaltaminteService {

    TipIncaltaminte save(TipIncaltaminte tipIncaltaminte);

    TipIncaltaminte update(TipIncaltaminte tipIncaltaminte);

    List<TipIncaltaminte> findAll();

    TipIncaltaminte findById(Integer id);

    boolean delete(TipIncaltaminte tipIncaltaminte);

    List<TipIncaltaminte> findAllTipIncaltaminteByDisponibilitate(Integer disponibilitate);

}

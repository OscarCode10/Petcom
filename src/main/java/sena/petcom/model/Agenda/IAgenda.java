package sena.petcom.model.Agenda;

import java.util.List;

public interface IAgenda {
    public void save (Agenda agenda);
    public List<Agenda> findAll();
    public Agenda findOne(Integer idAgenda);
}

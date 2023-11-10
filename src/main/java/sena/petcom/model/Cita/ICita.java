package sena.petcom.model.Cita;

import java.util.List;

public interface ICita {
    public void save (Cita cita);
    public List<Cita> findAll();
    public Cita findOne(Integer idCita);
}

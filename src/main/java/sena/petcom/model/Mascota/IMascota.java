package sena.petcom.model.Mascota;

import java.util.List;

public interface IMascota {
    public void save (Mascota masco);
    public List<Mascota> findAll();
    public Mascota findOne(Integer idMascota);
}

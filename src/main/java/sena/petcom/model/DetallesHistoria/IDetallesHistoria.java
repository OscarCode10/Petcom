package sena.petcom.model.DetallesHistoria;

import java.util.List;

public interface IDetallesHistoria {
    public void save (DetallesHistoria deta);
    public List<DetallesHistoria> findAll();
    public DetallesHistoria findOne(Integer idDetallesHistoria);
}

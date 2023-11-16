package sena.petcom.model.Cliente;

import java.util.List;

public interface ICliente {
    public void save (Cliente clien);
    public List<Cliente> findAll();
    public Cliente findOne(Integer idCliente);
}

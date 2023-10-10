package sena.petcom.model.agendaUsuario;

import java.util.List;


public interface IagendaUsuario {
    public void save (agendaUsuario agendaUsu);
    public List<agendaUsuario> findAll();
    public agendaUsuario findOne(Integer idAgendaUsuario);
}

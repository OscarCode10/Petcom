package sena.petcom.model.AgendaUsuario;

import java.util.List;

public interface IAgendaUsuario {
    public void save (AgendaUsuario agendaUsu);
    public List<AgendaUsuario> findAll();
    public AgendaUsuario findOne(Integer idAgendaUsuario);
}

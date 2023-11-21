package sena.petcom.model.AgendaUsuario;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class AgendaUsuarioDAO implements IAgendaUsuario {
    @PersistenceContext
    private EntityManager em;

    @Transactional()
    @Override
    public void save(AgendaUsuario agendaUsu) {
        if (agendaUsu.getIdAgendaUsuario() != null && agendaUsu.getIdAgendaUsuario()>0){
            em.merge(agendaUsu);
        } else {
            em.persist(agendaUsu);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<AgendaUsuario> findAll() {
        return em.createQuery("from AgendaUsuario").getResultList();
    }

    @Override
    public AgendaUsuario findOne(Integer idAgendaUsuario) {
        return em.find(AgendaUsuario.class, idAgendaUsuario);
    }
    
}
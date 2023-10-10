package sena.petcom.model.agendaUsuario;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class agendaUsuarioDAO implements IagendaUsuario {
    @PersistenceContext
    private EntityManager em;


    @Transactional()
    @Override
    public void save(agendaUsuario agendaUsu) {
        if (agendaUsu.getIdAgendaUsuario() != null && agendaUsu.getIdAgendaUsuario()>0){
            em.merge(agendaUsu);
        } else {
            em.persist(agendaUsu);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<agendaUsuario> findAll() {
        return em.createQuery("from AgendaUsuario").getResultList();
    }

    @Override
    public agendaUsuario findOne(Integer idAgendaUsuario) {
        return em.find(agendaUsuario.class, idAgendaUsuario);
    }
    
}

package sena.petcom.model.Cita;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CitaDAO implements ICita {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Cita cita) {
        if (cita.getIdCita() != null && cita.getIdCita() > 0) {
            em.merge(cita);
        } else {
            em.persist(cita);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Cita> findAll() {
        return em.createQuery("from Cita", Cita.class).getResultList();
    }

    @Transactional
    @Override
    public Cita findOne(Integer idCita) {
        return em.find(Cita.class, idCita);
    }
}

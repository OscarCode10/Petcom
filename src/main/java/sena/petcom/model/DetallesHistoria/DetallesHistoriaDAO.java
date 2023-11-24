package sena.petcom.model.DetallesHistoria;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class DetallesHistoriaDAO implements IDetallesHistoria {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<DetallesHistoria> findAll() {
        return em.createQuery("from DetallesHistoria").getResultList();
    }

    @Transactional
    @Override
    public DetallesHistoria findOne(Integer idDetallesHistoria) {
        return em.find(DetallesHistoria.class, idDetallesHistoria);
    }

    @Transactional
    @Override
    public void save(DetallesHistoria deta) {
        if (deta.getIdDetallesHistoria() != null && deta.getIdDetallesHistoria() > 0) {
            em.merge(deta);
        } else {
            em.persist(deta);
        }
    }
}

package sena.petcom.model.HistoriaClinica;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class HistoriaClinicaDAO implements IHistoriaClinica{
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Transactional()
    @Override
    public List<HistoriaClinica> findAll() {
        
        return em.createQuery("from HistoriaClinica").getResultList();
    }

    @Override
    public HistoriaClinica findOne(Integer idHistoriaClinica) {
        return em.find(HistoriaClinica.class, idHistoriaClinica);

    }
   
    @Transactional
    @Override
    public void save(HistoriaClinica histo) {
        if(histo.getIdHistoriaClinica() != null && histo.getIdHistoriaClinica() > 0){
            em.merge(histo);
        } else {
            em.persist(histo);
        }
    }
    
    

}

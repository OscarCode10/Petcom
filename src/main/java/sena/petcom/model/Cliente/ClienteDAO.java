package sena.petcom.model.Cliente;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ClienteDAO implements ICliente{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Integer idCliente) {
        // TODO Auto-generated method stub
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override    
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    @Transactional
    @Override
    public Cliente findOne(Integer idCliente) { 
        return em.find(Cliente.class, idCliente);
    }

    @Transactional
    @Override
    public void save(Cliente clien) {
        if (clien.getIdCliente()!=null && clien.getIdCliente()>0) {
            em.merge(clien);
        }else{
            em.persist(clien);
        }
    }
}

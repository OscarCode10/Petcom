package sena.petcom.model.Rol;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RolDAO implements IRol{
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Rol rol) {
        if(rol.getIdRol() !=null && rol.getIdRol()>0){
            em.merge(rol);
        }
        else{
            em.persist(rol);
        }
    }
}

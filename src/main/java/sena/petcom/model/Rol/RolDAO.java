package sena.petcom.model.Rol;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RolDAO implements IRol{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Integer idRol) {
    }

    @Override
    public List<Rol> findAll() {
        return null;
    }

    @Override
    public Rol findOne(Integer idRol) {
        return null;
    }

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

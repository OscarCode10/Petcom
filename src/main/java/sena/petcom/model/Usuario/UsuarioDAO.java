package sena.petcom.model.Usuario;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UsuarioDAO implements IUsuario{
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void save(Usuario user) {
        if(user.getIdUsuario() !=null && user.getIdUsuario()>0){
            em.merge(user);
        }
        else{
            em.persist(user);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional()
    @Override
    public List<Usuario> findAll() {
        return em.createQuery("from Usuario").getResultList();
    }

    @Override
    @Transactional
    public Usuario findOne(Integer idUsuario) {
        return em.find(Usuario.class, idUsuario);
    }

    @Override
    public Integer login(String correoUsuContro, String claveUsuContro) {
        TypedQuery<Usuario> query=em.createQuery("select u from Usuario u where u.correoUsu=:correo", Usuario.class);
        query.setParameter("correo", correoUsuContro);
        List<Usuario> resultados=query.getResultList();
        if (!resultados.isEmpty()) {
            Usuario userFound=resultados.get(0);
            if (userFound.getClaveUsu().equals(claveUsuContro)) {
                return userFound.getIdUsuario();
            }else{
                return null;
            }
        } else {
            return null;
        }
    }
}

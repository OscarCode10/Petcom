    package sena.petcom.model.Mascota;

    import java.util.List;

    import org.springframework.stereotype.Repository;

    import jakarta.persistence.EntityManager;
    import jakarta.persistence.PersistenceContext;
    import jakarta.transaction.Transactional;

    @Repository
    public class MascotaDAO implements IMascota{
        @PersistenceContext
        private EntityManager em;

        @Override
        public void delete(Integer idMascota) {
            // TODO Auto-generated method stub
            
        }

        @SuppressWarnings("unchecked")
        @Transactional()
        @Override
        public List<Mascota> findAll() {
            return em.createQuery("from Mascota").getResultList();
        }

        @Transactional
        @Override
        public Mascota findOne(Integer idMascota) {
            return em.find(Mascota.class, idMascota);
        }

        @Transactional
        @Override
        public void save(Mascota masco) {
            if(masco.getIdMascota()!=null && masco.getIdMascota()>0){
                em.merge(masco);    
            }
            else{
                em.persist(masco);
            }
            
        }



        
    }

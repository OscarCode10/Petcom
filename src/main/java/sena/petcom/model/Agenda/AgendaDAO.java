package sena.petcom.model.Agenda;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class AgendaDAO implements IAgenda{
    @PersistenceContext
    private EntityManager em;

    @Transactional()
    @Override
    public void save(Agenda agenda) {
        if (agenda.getIdAgenda() != null && agenda.getIdAgenda()>0) {
            em.merge(agenda);
        }else{
            em.persist(agenda);
        }
        
    }

    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Agenda> findAll() {
        return em.createQuery("from Agenda").getResultList();
    }

    @Transactional
    @Override
    public Agenda findOne(Integer idAgenda) {
        return em.find(Agenda.class, idAgenda);
    }

    @Override
    public Date parseDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Time parseHour(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new Time(date.getTime());
    }
}
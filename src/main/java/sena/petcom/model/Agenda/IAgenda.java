package sena.petcom.model.Agenda;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface IAgenda {
    public void save (Agenda agenda);
    public List<Agenda> findAll();
    public Agenda findOne(Integer idAgenda);
    public Date parseDate(String date);
    public Time parseHour(String time);
}

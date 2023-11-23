package sena.petcom.model.HistoriaClinica;

import java.util.List;

public interface IHistoriaClinica {
    public void save(HistoriaClinica histo);
    public List<HistoriaClinica> findAll();
    public HistoriaClinica findOne(Integer idHistoriaClinica);
}

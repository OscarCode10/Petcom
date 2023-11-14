package sena.petcom.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

public interface IUsuario {
    public void save (Usuario user);
    public List<Usuario> findAll();
    public Usuario findOne(Integer idUsuario);
    public void delete(Integer idUsuario);
    @Query("select u from Usuario u where correoUsu=?1")
    public Integer login(String correoUsuContro, String claveUsuContro);
}

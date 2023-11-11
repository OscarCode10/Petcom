package sena.petcom.model.Usuario;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sena.petcom.model.Rol.Rol;
import sena.petcom.model.agendaUsuario.agendaUsuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUsuario")
    private Integer idUsuario;
    
    @NotEmpty
    @NotNull
    @Column(name="tipoDocumentoUsu")
    private String tipoDocumentoUsu;

    @NotNull
    @Column(name="numDocumentoUsu", unique=true)
    private Integer numDocumentoUsu;

    @NotEmpty
    @NotNull
    @Column(name="nombreUsu")
    private String nombreUsu;

    @NotEmpty
    @NotNull
    @Column(name="apellidoUsu")
    private String apellidoUsu;

    @NotNull
    @Column(name="telefonoUsu", unique=true)
    private Long telefonoUsu;

    @NotEmpty
    @NotNull
    @Column(name="correoUsu", unique=true)
    private String correoUsu;

    @NotEmpty
    @NotNull
    @Column(name="claveUsu")
    private String claveUsu;
    
    @NotNull
    @Column(name="estadoUsu")
    private Boolean estadoUsu;

    @ManyToOne(fetch=FetchType.LAZY)
    private Rol FK;

    @OneToMany(mappedBy="FK", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<agendaUsuario> idAgendaUsuarioFK;
}

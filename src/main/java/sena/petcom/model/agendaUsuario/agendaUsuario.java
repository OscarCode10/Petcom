package sena.petcom.model.AgendaUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sena.petcom.model.Agenda.Agenda;
import sena.petcom.model.Usuario.Usuario;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="AgendaUsuario")
public class AgendaUsuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAgendaUsuario;

    @NotEmpty
    @Column
    @NotNull
    private String tipoCita;
    
    @ManyToOne(fetch=FetchType.LAZY)
    private Usuario FK;

    @ManyToOne(fetch=FetchType.LAZY)
    private Agenda FkA;
}
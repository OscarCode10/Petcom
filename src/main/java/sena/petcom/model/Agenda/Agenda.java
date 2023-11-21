package sena.petcom.model.Agenda;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sena.petcom.model.AgendaUsuario.AgendaUsuario;
import sena.petcom.model.Cita.Cita;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idAgenda;

    @NotNull
    @Column
    private LocalDate fechaInicio;

    @NotNull
    @Column
    private LocalDate fechaFin;

    @NotNull
    @Column
    private Time horaInicio;

    @NotNull
    @Column
    private Time horaFin;

    @NotNull
    @Column
    private Boolean estadoAgenda;

    @OneToMany(mappedBy="FkA", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<AgendaUsuario> idAgendaUsuarioFK;

    @OneToMany(mappedBy="FK", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Cita> idCitaFK;

}

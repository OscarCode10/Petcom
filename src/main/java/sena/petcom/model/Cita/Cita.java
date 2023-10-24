package sena.petcom.model.Cita;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sena.petcom.model.Agenda.Agenda;
import sena.petcom.model.Cliente.Cliente;
import sena.petcom.model.DetallesHistoria.DetallesHistoria;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
    @Table(name="Cita")
    public class Cita {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Integer idCita;

        @NotNull
        @Column
        private String motivoConsulta;

        @NotNull
        @Column
        private Boolean estadoCita;

        @ManyToOne(fetch=FetchType.LAZY)
        private Agenda FK;

        @ManyToOne(fetch=FetchType.LAZY)
        private Cliente Fk;

        @OneToMany(mappedBy="Fk", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
        private List<DetallesHistoria> idDetallesHistoriaFK;
    }

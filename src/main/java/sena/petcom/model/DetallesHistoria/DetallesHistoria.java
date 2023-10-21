package sena.petcom.model.DetallesHistoria;

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
import sena.petcom.model.Cita.Cita;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="DetallesHistoria")
public class DetallesHistoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDetallesHistoria;

    @NotNull
    @Column
    private int tamañoDetalles;
    
    @NotNull
    @Column
    private int pesoDetalles;

    @NotNull
    @NotEmpty
    @Column
    private String antecedentesDetalles;

    @NotNull
    @NotEmpty
    @Column
    private String recomendaciones;

    @NotNull
    @Column
    private Boolean estadoDetalles;

    @ManyToOne(fetch=FetchType.LAZY)
    private HistoriaClinica FK;

    @ManyToOne(fetch=FetchType.LAZY)
    private Cita Fk;
}

package sena.petcom.model.HistoriaClinica;

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
import sena.petcom.model.DetallesHistoria.DetallesHistoria;
import sena.petcom.model.Mascota.Mascota;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="HistoriaClinica")
public class HistoriaClinica {
    private Long idMascota;

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idHistoriaClinica;

    @NotNull
    @Column
    private int peso;

    @NotNull
    @Column
    private int tamano;

    @NotNull
    @NotEmpty
    @Column
    private String enfermedades;

    @NotNull
    @NotEmpty
    @Column
    private String antecedentes;

    @NotNull
    @NotEmpty
    @Column
    private String diagnosticoHistoria;

    @ManyToOne(fetch=FetchType.LAZY)
    private Mascota FK;

    @OneToMany(mappedBy="FK", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<DetallesHistoria> idDetallesHistoriaFK;
}

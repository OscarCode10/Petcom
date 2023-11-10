package sena.petcom.model.Mascota;

import java.sql.Date;
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
import sena.petcom.model.Cliente.Cliente;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Mascota")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMascota;

    @NotNull
    @NotEmpty
    @Column
    private String nombreMascota;

    @NotNull
    @NotEmpty
    @Column
    private String apellidoMascota;

    @NotNull
    @Column
    private Date fechaNacimiento;

    @NotNull
    @NotEmpty
    @Column
    private String raza;
    
    @NotNull
    @NotEmpty
    @Column
    private String genero;

    @NotNull
    @Column
    private Boolean estadoMascota;

    @ManyToOne(fetch=FetchType.LAZY)
    private Cliente FK;

    @OneToMany(mappedBy="FK", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<HistoriaClinica> idHistoriaClinicaFK;
   
}

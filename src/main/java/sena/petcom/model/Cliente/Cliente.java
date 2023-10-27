package sena.petcom.model.Cliente;

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
import sena.petcom.model.Cita.Cita;
import sena.petcom.model.Mascota.Mascota;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @NotNull
    @Column
    private String tipoDocCliente;

    @NotNull
    @Column(unique=true)
    private int numDocCliente;

    @NotNull
    @Column
    private String nombreCliente;

    @NotNull
    @Column(unique=true)
    private int telefonoCliente;

    @NotNull
    @Column(unique=true)
    private String correoCliente;

    @NotNull
    @Column
    private Boolean estadoCliente;

    @OneToMany(mappedBy="Fk", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Cita> idCitaFK;

    @OneToMany(mappedBy="FK", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Mascota> idMascotaFK;

    public static Cliente findOne(Integer idCliente2) {
        return null;
    }
}

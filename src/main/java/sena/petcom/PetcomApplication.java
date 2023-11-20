package sena.petcom;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import sena.petcom.model.Agenda.Agenda;
import sena.petcom.model.Agenda.IAgenda;
import sena.petcom.model.Cliente.Cliente;
import sena.petcom.model.Cliente.ICliente;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;
import sena.petcom.model.HistoriaClinica.IHistoriaClinica;
import sena.petcom.model.Mascota.IMascota;
import sena.petcom.model.Mascota.Mascota;
import sena.petcom.model.Rol.IRol;
import sena.petcom.model.Rol.Rol;
import sena.petcom.model.Usuario.IUsuario;
import sena.petcom.model.Usuario.Usuario;
import sena.petcom.model.agendaUsuario.IAgendaUsuario;
import sena.petcom.model.agendaUsuario.AgendaUsuario;

@SpringBootApplication
@ServletComponentScan
public class PetcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetcomApplication.class, args);
	}

	@Autowired
	IUsuario iusuario;

	@Autowired
	IRol irol;

	@Autowired
	ICliente icliente;

	@Autowired
	IMascota iMascota;

	@Autowired
	IAgenda iagenda;
	
	@Autowired
	IAgendaUsuario iagendaUsuario;

	@Autowired
	IHistoriaClinica iHistoriaClinica;

	@Bean
	CommandLineRunner init(){
		return args -> {
			Rol rol = Rol.builder()
				.rol("Administrador")
				.estadoRol(true)
				.build();

			Rol rol1 = Rol.builder()
				.rol("Recepcionista")
				.estadoRol(true)
				.build();
			
			Rol rol2 = Rol.builder()
				.rol("Doctor")
				.estadoRol(true)
				.build();

			Rol rol3 = Rol.builder()
				.rol("Peluquero")
				.estadoRol(true)
				.build();

			irol.save(rol);
			irol.save(rol1);
			irol.save(rol2);
			irol.save(rol3);

			Usuario admin = Usuario.builder()
				.tipoDocumentoUsu("C.C.")
				.numDocumentoUsu(1019019842)
				.nombreUsu("Oscar")
				.apellidoUsu("Ortiz")
				.telefonoUsu(3223496849L)
				.correoUsu("osca@a.a")
				.claveUsu("123")
				.estadoUsu(true)
				.FK(rol)
				.build();

			iusuario.save(admin);

			Usuario doctor = Usuario.builder()
				.tipoDocumentoUsu("C.C.")
				.numDocumentoUsu(1031647371)
				.nombreUsu("Samuel")
				.apellidoUsu("Cano")
				.telefonoUsu(3027834956L)
				.correoUsu("samuel@a.a")
				.claveUsu("123")
				.estadoUsu(true)
				.FK(rol2)
				.build();

			iusuario.save(doctor);

			Cliente cliente = Cliente.builder()
				.tipoDocCliente("C.C.")
				.numDocCliente(1019019842)
				.nombreCliente("Oscar Ortiz")
				.telefonoCliente(322349683)
				.correoCliente("osca@a.a")
				.estadoCliente(true)
				.build();

			icliente.save(cliente);
			
			Mascota mascota = Mascota.builder()
				.nombreMascota("Nekko")
				.apellidoMascota("Ortiz")
				.fechaNacimiento(Date.valueOf(LocalDate.of(2023, 11, 15)))
				.raza("Persa")
				.genero("M")
				.estadoMascota(true)
				.FK(cliente)
				.build();

			iMascota.save(mascota);
			
			Agenda agenda = Agenda.builder()
			 	.fechaInicio(Date.valueOf(LocalDate.of(2023, 11, 15)))
			 	.fechaFin(Date.valueOf(LocalDate.of(2023, 11, 15)))
			 	.horaInicio(Time.valueOf("10:00:00"))
			 	.horaFin(Time.valueOf("11:00:00"))
				.estadoAgenda(true)
				.build();

			iagenda.save(agenda);

			AgendaUsuario agendausuario = AgendaUsuario.builder()
				.tipoCita("Médica")
				.FK(doctor)
				.FkA(agenda)
				.build();

			iagendaUsuario.save(agendausuario);
			
			HistoriaClinica historiaClinica = HistoriaClinica.builder()
				.peso(5)
				.tamano(40)
				.enfermedades("Ninguna")
				.antecedentes("Inyecciónes completas")
				.diagnosticoHistoria("El gato se encuentra en buen estado")
				.FK(mascota)
				.build();

			iHistoriaClinica.save(historiaClinica);
		};
	}
}

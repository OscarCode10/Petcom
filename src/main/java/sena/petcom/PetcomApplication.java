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
import sena.petcom.model.AgendaUsuario.AgendaUsuario;
import sena.petcom.model.AgendaUsuario.IAgendaUsuario;
import sena.petcom.model.Cita.Cita;
import sena.petcom.model.Cita.ICita;
import sena.petcom.model.Cliente.Cliente;
import sena.petcom.model.Cliente.ICliente;
import sena.petcom.model.DetallesHistoria.DetallesHistoria;
import sena.petcom.model.DetallesHistoria.IDetallesHistoria;
import sena.petcom.model.HistoriaClinica.HistoriaClinica;
import sena.petcom.model.HistoriaClinica.IHistoriaClinica;
import sena.petcom.model.Mascota.IMascota;
import sena.petcom.model.Mascota.Mascota;
import sena.petcom.model.Rol.IRol;
import sena.petcom.model.Rol.Rol;
import sena.petcom.model.Usuario.IUsuario;
import sena.petcom.model.Usuario.Usuario;

@SpringBootApplication
@ServletComponentScan
public class PetcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetcomApplication.class, args);
	}

	@Autowired
	IUsuario iUsuario;

	@Autowired
	IRol iRol;

	@Autowired
	ICliente iCliente;

	@Autowired
	IMascota iMascota;

	@Autowired
	IAgenda iAgenda;
	
	@Autowired
	IAgendaUsuario iAgendaUsuario;

	@Autowired
	IHistoriaClinica iHistoriaClinica;

	@Autowired
	ICita iCita;

	@Autowired
	IDetallesHistoria iDetallesHistoria;

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

			iRol.save(rol);
			iRol.save(rol1);
			iRol.save(rol2);
			iRol.save(rol3);

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

			iUsuario.save(admin);

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

			iUsuario.save(doctor);

			Cliente cliente = Cliente.builder()
				.tipoDocCliente("C.C.")
				.numDocCliente(1019019842)
				.nombreCliente("Oscar Ortiz")
				.telefonoCliente(322349683)
				.correoCliente("osca@a.a")
				.estadoCliente(true)
				.build();

			iCliente.save(cliente);
			
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
			 	.fechaInicio(LocalDate.of(2023, 11, 20))
				.fechaFin((LocalDate.of(2023, 11, 26)))
			 	.horaInicio(Time.valueOf("9:00:00"))
			 	.horaFin(Time.valueOf("18:00:00"))
				.estadoAgenda(true)
				.build();

			iAgenda.save(agenda);

			AgendaUsuario agendausuario = AgendaUsuario.builder()
				.tipoCita("Médica")
				.FK(doctor)
				.FkA(agenda)
				.build();

			iAgendaUsuario.save(agendausuario);

			Cita cita = Cita.builder()
				.motivoConsulta("Vacuna")
				.fechaCita(Date.valueOf(LocalDate.of(2023, 11, 20)))
				.horaCita(Time.valueOf("09:17:00"))
				.estadoCita(true)
				.FK(agenda)
				.FkC(cliente)
				.build();

			iCita.save(cita);
			
			HistoriaClinica historiaClinica = HistoriaClinica.builder()
				.peso(5)
				.tamano(40)
				.enfermedades("Ninguna")
				.antecedentes("Inyecciónes completas")
				.diagnosticoHistoria("El gato se encuentra en buen estado")
				.FK(mascota)
				.build();

			iHistoriaClinica.save(historiaClinica);

			DetallesHistoria detallesHistoria = DetallesHistoria.builder()
				.pesoDetalles(6)
				.tamanoDetalles(30)
				.diagnosticoDetalles("Engordo")
				.FK(historiaClinica)
				.FkC(cita)
				.build();

			iDetallesHistoria.save(detallesHistoria);
		};
	}
}

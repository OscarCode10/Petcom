package sena.petcom;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sena.petcom.model.Agenda.Agenda;
import sena.petcom.model.Agenda.IAgenda;
import sena.petcom.model.Cliente.Cliente;
import sena.petcom.model.Cliente.ICliente;
import sena.petcom.model.Rol.IRol;
import sena.petcom.model.Rol.Rol;
import sena.petcom.model.Usuario.IUsuario;
import sena.petcom.model.Usuario.Usuario;

@SpringBootApplication
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
	IAgenda iagenda;

	@Bean
	CommandLineRunner init(){
		return args -> {
			Rol rol=Rol.builder()
				.rol("Administrador")
				.estadoRol(true)
				.build();

			Rol rol1=Rol.builder()
				.rol("Recepcionista")
				.estadoRol(true)
				.build();
			
			Rol rol2=Rol.builder()
				.rol("Doctor")
				.estadoRol(true)
				.build();

			Rol rol3=Rol.builder()
				.rol("Peluquero")
				.estadoRol(true)
				.build();

			irol.save(rol);
			irol.save(rol1);
			irol.save(rol2);
			irol.save(rol3);

			Usuario usuario=Usuario.builder()
				.tipoDocumentoUsu("C.C.")
				.numDocumentoUsu(12312312)
				.nombreUsu("Oscar")
				.apellidoUsu("Ortiz")
				.telefonoUsu(1231231)
				.correoUsu("osca@a.a")
				.claveUsu("123")
				.estadoUsu(true)
				.FK(rol)
				.build();

			iusuario.save(usuario);


			Cliente cliente=Cliente.builder()
				.tipoDocCliente("C.C.")
				.numDocCliente(1019019842)
				.nombreCliente("Oscar Ortiz")
				.telefonoCliente(322349683)
				.correoCliente("osca@a.a")
				.estadoCliente(true)
				.build();

			icliente.save(cliente);
		};
	}
}

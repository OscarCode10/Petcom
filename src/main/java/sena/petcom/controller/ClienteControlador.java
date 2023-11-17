package sena.petcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import sena.petcom.model.Cliente.ICliente;
import sena.petcom.model.Cliente.Cliente;

@Controller
public class ClienteControlador {
    
    @Autowired
    private ICliente iCliente;

    @GetMapping("/moduloCliente")
    public String moduloCliente(Model m){
        return "cliente/moduloCliente";
    }

    @GetMapping("/registrarClienteV")
    public String registrarClienteV(Model m){
        m.addAttribute("cliente", new Cliente());
        return "cliente/registrarCliente";
    }

    @PostMapping("registrarCliente")
    public String registrarCliente(@Validated Cliente clien, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "redirect:/registrarClienteV";
        }else{
            iCliente.save(clien);
            status.setComplete();
            return "redirect:/moduloCliente";
        }
    }

    @GetMapping("/listarCliente")
    public String listarCliente(Model m) {
        m.addAttribute("clientes", iCliente.findAll());
        return "cliente/listarCliente";
    }

    @GetMapping("/modificarClienteV/{idCliente}")
    public String modificarClienteV(@PathVariable Integer idCliente, Model m){
        Cliente cliente=null;
        if(idCliente>0){
            cliente=iCliente.findOne(idCliente);
            m.addAttribute("cliente",cliente);
            return "cliente/modificarCliente";
        }else{
            return "redirect:/listarCliente";
        }
    }

    @PostMapping("/modificarCliente")
    public String modificarCliente(@Validated Cliente clien, BindingResult res, Model m, SessionStatus status){
        if (res.hasErrors()) {
            return "redirect:/modificarClienteV/{idCliente}";
        }else{
            iCliente.save(clien);
            status.setComplete();
            return "redirect:/listCliente";
        }
    }

}

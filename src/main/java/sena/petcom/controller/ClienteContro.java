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
public class ClienteContro {
    
    @Autowired
    private ICliente iCliente;


    @GetMapping("/modulCliente")
    public String mo(Model m){
        return "modulCliente";
    }

    @GetMapping("/registrarClienteV")
    public String re(Model m){
        m.addAttribute("cliente", new Cliente());
        return "registrarCliente";
    }

    @PostMapping("registrarCliente")
    public String addU(@Validated Cliente clien, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "redirect:/registrarClienteV";
        }else{
            iCliente.save(clien);
            status.setComplete();
            return "redirect:/modulCliente";
        }
    }

    @GetMapping("/listCliente")
    public String listar(Model m) {
        m.addAttribute("clientes", iCliente.findAll());
        return "/listCliente";
    }

    @GetMapping("/modificarClienteV/{idCliente}")
    public String verModi(@PathVariable Integer idCliente, Model m){
        Cliente cliente=null;
        if(idCliente>0){
            cliente=iCliente.findOne(idCliente);
            m.addAttribute("cliente",cliente);
            return "modificarCliente";
        }else{
            return "redirect:/listCliente";
        }
    }

    @PostMapping("/modificarCliente")
    public String modi(@Validated Cliente clien, BindingResult res, Model m, SessionStatus status){
        if (res.hasErrors()) {
            return "redirect:/modificarClienteV/{idCliente}";
        }else{
            iCliente.save(clien);
            status.setComplete();
            return "redirect:/listCliente";
        }
    }

}

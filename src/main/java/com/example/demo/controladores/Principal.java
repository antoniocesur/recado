package com.example.demo.controladores;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.Recado;
import com.example.demo.servicios.ServicioAutor;
import com.example.demo.servicios.ServicioRecado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Controller
public class Principal {
    @Autowired
    ServicioRecado servicioRecado;
    @Autowired
    ServicioAutor servicioAutor;

    @GetMapping("/hola-mundo")
    @ResponseBody
    public String helloWorld(Model model){
        return "Bienvenido a mi Universo";
    }

    @GetMapping("/")
    public String index(Model model){
        for(Recado recado: servicioRecado.findAll()){
            System.out.println(recado.toString());
        }
        model.addAttribute("saludo", "¡Hola murciano!");
        model.addAttribute("listaRecados", servicioRecado.findAll());
        model.addAttribute("recado", new Recado()); //Esto es para que el formulario tenga el objeto recado vacío para devolverlo si escriben un nuevo recado
        return "index.html";
    }

    @PostMapping("/")
    public String nuevoRecado(@ModelAttribute("recado") Recado nuevoRecado,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/";
        } else {
            //Si nos envían contenido desde el formulario, lo añadimos a la BBDD, tenemos que poner también un usuario y una fecha
            Autor admin=servicioAutor.findByNombre("admin");
            nuevoRecado.setAutor(admin);
            nuevoRecado.setFecha(Date.valueOf(LocalDate.now()));
            servicioRecado.save(nuevoRecado);
            return "redirect:/";
        }
    }

    @GetMapping("/megusta/{id}")
    public String meGusta(@PathVariable long id){


        return "redirect:/";
    }
}

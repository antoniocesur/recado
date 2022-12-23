package com.example.demo.controladores;

import com.example.demo.modelo.Recado;
import com.example.demo.servicios.ServicioRecado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;

@Controller
public class Principal {
    @Autowired
    ServicioRecado servicioRecado;

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
        return "index.html";
    }

    /*@PostMapping("/")
    public String nuevaAsignatura(@Valid @ModelAttribute("formAsignatura") Asignatura nuevaAsignatura,
                                  BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "crud/FormAsignatura";
        } else {
            if (!file.isEmpty()) {
                String imagen = storageService.store(file, nuevaAsignatura.getNombre());
                System.out.println("La imagen a guardar es : " + imagen);
                nuevaAsignatura.setImagen(MvcUriComponentsBuilder
                        .fromMethodName(ControladorFile.class, "serveFile", imagen).build().toUriString());
            }
            servicioAsignatura.add(nuevaAsignatura);
            return "redirect:/asignatura";
        }
    }*/
}

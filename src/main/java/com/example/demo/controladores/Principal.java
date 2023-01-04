package com.example.demo.controladores;

import com.example.demo.modelo.Autor;
import com.example.demo.modelo.MeGusta;
import com.example.demo.modelo.Recado;
import com.example.demo.modelo.UserDto;
import com.example.demo.servicios.ServicioAutor;
import com.example.demo.servicios.ServicioMeGusta;
import com.example.demo.servicios.ServicioRecado;
import com.example.demo.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Controller
public class Principal {
    @Autowired
    ServicioRecado servicioRecado;
    @Autowired
    UserService servicioAutor;
    @Autowired
    ServicioMeGusta servicioMeGusta;

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
        List<Recado> listaRecados=servicioRecado.findAll();

        ////Compruebo los recados que le gustan al usuario activo para cambiar el color del corazón
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println("Nombre usuario: " + currentUserName);
            Autor autor = servicioAutor.findByEmail(currentUserName);
            for (Recado recado : listaRecados) {
                recado.setMeGustaActual(servicioMeGusta.activoRecadoAndAutor(recado, autor));
            }
        }
        model.addAttribute("listaRecados", listaRecados);
        model.addAttribute("recado", new Recado()); //Esto es para que el formulario tenga el objeto recado vacío para devolverlo si escriben un nuevo recado
        return "index";
    }

    @PostMapping("/")
    public String nuevoRecado(@ModelAttribute("recado") Recado nuevoRecado,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/";
        } else {
            //Si nos envían contenido desde el formulario, lo añadimos a la BBDD, tenemos que poner también un usuario y una fecha
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                System.out.println("Nombre usuario: " + currentUserName);
                Autor autor = servicioAutor.findByEmail(currentUserName);
                nuevoRecado.setAutor(autor);
                nuevoRecado.setFecha(Date.valueOf(LocalDate.now()));
                servicioRecado.save(nuevoRecado);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/megusta/{id}")
    public String meGusta(@PathVariable long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println("Nombre usuario: " + currentUserName);
            Autor autor = servicioAutor.findByEmail(currentUserName);

            //Autor autor=servicioAutor.findByNombre("admin");
            Recado recado = servicioRecado.findById(id);
            MeGusta meGusta = servicioMeGusta.findByRecadoAndAutor(recado, autor);
            if (meGusta == null) {
                //Si el "me gusta" no existe, se crea y se pone el estado a true
                meGusta = new MeGusta();
                meGusta.setAutor(autor);
                meGusta.setRecado(recado);
                meGusta.setEstado(true);
            } else {
                //Si el me gusta ya existe, modifico el estado
                meGusta.setEstado(!meGusta.isEstado());
            }
            servicioMeGusta.save(meGusta);
        }
        return "redirect:/" + "#" + id;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        Autor existing = servicioAutor.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        servicioAutor.saveUser(user);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = servicioAutor.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}

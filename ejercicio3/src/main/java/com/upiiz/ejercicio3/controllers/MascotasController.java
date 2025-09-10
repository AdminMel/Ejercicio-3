package com.upiiz.ejercicio3.controllers;

import com.upiiz.ejercicio3.models.Mascota;
import com.upiiz.ejercicio3.services.MascotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MascotasController {
    //Mostrar el listado
    MascotaService mascotaService = new MascotaService();
    @GetMapping("/")
    public String mascotas(Model model) {
        model.addAttribute("mascotas", mascotaService.findAll());
        return "mascotas-listado";
    }
    //Guardar una mascota
    @GetMapping("/mascotas/agregar")
    //1. Mostar el formulario
    public String nuevaMascotaFormulario(Model model) {
        Mascota mascota = new Mascota();
        model.addAttribute("mascota", mascota);
        return "mascotas-agregar";
    }
    //2. Guardar la mascota y redireccionar al listado
    @PostMapping("/mascotas/agregar")
    public String nuevaMascotaAgregar(@ModelAttribute("mascota") Mascota mascota) {
        mascotaService.save(mascota);
        return "redirect:/";
    }
    //Eliminar una mascota
    //1. Mostrar la mascota en el formulario
    @GetMapping("/mascotas/eliminar/{id}")
    public String eliminaMascotaFormulario(Model model, @PathVariable int id) {
        Mascota mascota = mascotaService.getMascota(id);
        if(mascota != null) {
            model.addAttribute("mascota", mascota);
            return "mascotas-eliminar";
        }else{
            return "redirect:/";
        }
    }
    //2. Eliminar la mascota y redireccionar al listado
    @PostMapping("/mascotas/eliminar")
    public String eliminarMascota(@ModelAttribute("mascota") Mascota mascota) {
        mascotaService.delete(mascota.getId());
        return "redirect:/";
    }
    //Actualizar una mascota
    //1. Mostrar el formulario
    @GetMapping("/mascotas/actualizar/{id}")
    public String ActualizaMascotaFormulario(Model model, @PathVariable int id) {
        Mascota mascota = mascotaService.getMascota(id);
        if(mascota != null) {
            model.addAttribute("mascota", mascota);
            return "mascotas-actualizar";
        }else{
            return "redirect:/";
        }
    }
    //2. Actualizar y redireccionar al listado
    @PostMapping("/mascotas/actualizar")
    public String actualizarMascota(@ModelAttribute("mascota") Mascota mascota) {
        mascotaService.update(mascota);
        return "redirect:/";
    }
}



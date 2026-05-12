package com.universidad.tareasapp.controller;

import com.universidad.tareasapp.entity.Tarea;
import com.universidad.tareasapp.repository.TareaRepository;
import com.universidad.tareasapp.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TareasViewController {

    private final TareaService tareaService;
    private final TareaRepository tareaRepository;

    public TareasViewController(TareaService tareaService, TareaRepository tareaRepository) {
        this.tareaService = tareaService;
        this.tareaRepository = tareaRepository;
    }

    @GetMapping("/tareas")
    public String listaTareas(Model model) {
        model.addAttribute("tareas", tareaRepository.findAll());
        return "tareas";
    }

    @GetMapping("/tareas/nueva")
    public String formularioNueva() {
        return "nueva-tarea";
    }

    @PostMapping("/tareas/nueva")
    public String crearTarea(@RequestParam String titulo,
                             @RequestParam String descripcion) {
        Tarea t = new Tarea();
        t.setTitulo(titulo);
        t.setDescripcion(descripcion);
        tareaService.crear(t);
        return "redirect:/tareas";
    }
}
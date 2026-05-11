package com.universidad.tareasapp.controller;

import com.universidad.tareasapp.entity.Tarea;
import com.universidad.tareasapp.service.TareaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService service;

    public TareaController(TareaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea tarea) {
        return ResponseEntity.ok(service.crear(tarea));
    }

    @PatchMapping("/{id}/completar")
    public ResponseEntity<Tarea> completar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.completar(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
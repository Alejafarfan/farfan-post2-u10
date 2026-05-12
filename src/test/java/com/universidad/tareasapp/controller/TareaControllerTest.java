package com.universidad.tareasapp.controller;

import com.universidad.tareasapp.entity.Tarea;
import com.universidad.tareasapp.service.TareaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TareaControllerTest {

    @Mock
    TareaService service;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        TareaController controller = new TareaController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void get_tareaExiste_retorna200() throws Exception {
        Tarea t = new Tarea();
        t.setId(1L);
        t.setTitulo("Test");
        when(service.buscarPorId(1L)).thenReturn(t);

        mockMvc.perform(get("/api/tareas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Test"));
    }

    @Test
    void get_noExiste_retorna404() throws Exception {
        when(service.buscarPorId(99L))
                .thenThrow(new EntityNotFoundException("no encontrada"));

        mockMvc.perform(get("/api/tareas/99"))
                .andExpect(status().isNotFound());
    }
    @Test
    void post_tareaValida_retorna200() throws Exception {
        Tarea t = new Tarea();
        t.setId(1L);
        t.setTitulo("Nueva");
        when(service.crear(any())).thenReturn(t);

        mockMvc.perform(post("/api/tareas")
                        .contentType("application/json")
                        .content("{\"titulo\":\"Nueva\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Nueva"));
    }

    @Test
    void patch_completar_retorna200() throws Exception {
        Tarea t = new Tarea();
        t.setId(1L);
        t.setCompletada(true);
        when(service.completar(1L)).thenReturn(t);

        mockMvc.perform(patch("/api/tareas/1/completar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completada").value(true));
    }
}
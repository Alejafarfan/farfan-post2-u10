package com.universidad.tareasapp.repository;

import com.universidad.tareasapp.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByCompletada(boolean completada);
}
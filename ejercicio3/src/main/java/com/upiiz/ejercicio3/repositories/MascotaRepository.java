package com.upiiz.ejercicio3.repositories;
import com.upiiz.ejercicio3.models.Mascota;
import org.springframework.stereotype.Repository;

import java.util.List;

//Menú de lo que se ofrece
@Repository
public interface MascotaRepository {
    // Regrese todas las mascotas
    public List<Mascota> findAll();
    // Regrese una mascota por ID
    public Mascota getMascota(int id);
    // Agregar una mascota
    public void save(Mascota mascota);
    // Eliminar una mascota
    public void delete(int id);
    // Actualizar una mascota
    public void update(Mascota mascota);
}

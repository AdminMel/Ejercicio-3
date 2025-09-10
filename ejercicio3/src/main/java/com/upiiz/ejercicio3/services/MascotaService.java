package com.upiiz.ejercicio3.services;

import com.upiiz.ejercicio3.models.Mascota;
import com.upiiz.ejercicio3.repositories.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Extend - Herencia - Heredar las propiedades de otra clase
//Implements - Usar o implementar los métodos de otra clase
@Service
public class MascotaService implements MascotaRepository {
    //Requerimos
    // 1. Acceso a una base de datos - Aún no
    // 2. Acceso a un listado en MEMORIA - Este sí
    // “BD” en memoria
    private final List<Mascota> mascotas;
    private int lastId = 0;
    public MascotaService() {
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(1,"Pluto","Mascota Micky",5));
        mascotas.add(new Mascota(2,"Firulais","Un perro muy noble",8));
        mascotas.add(new Mascota(3,"Scoby Doo","Un perro muy miedoso",8));
        lastId = 3;
    }

    @Override
    public List<Mascota> findAll() {
        return List.copyOf(mascotas); // snapshot inmutable
    }

    @Override
    public Mascota getMascota(int id) {
        return mascotas.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Mascota mascota) {
        lastId ++;
        Objects.requireNonNull(mascota, "mascota no puede ser null");
        mascota.setId(lastId);
        mascotas.add(mascota);
    }

    @Override
    public void delete(int id) {
        boolean removed = mascotas.removeIf(m -> m.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("No existe una mascota con id=" + id);
        }
    }

    @Override
    public void update(Mascota mascota) {
        Objects.requireNonNull(mascota, "mascota no puede ser null"); // <-- mover arriba
        boolean exists = mascotas.stream().anyMatch(m -> m.getId() == mascota.getId());
        if (!exists) {
            throw new IllegalArgumentException("No existe una mascota con id=" + mascota.getId());
        }
        mascotas.replaceAll(actual ->
                actual.getId() == mascota.getId() ? merge(actual, mascota) : actual
        );
    }

    private Mascota merge(Mascota anterior, Mascota nuevo) {
        return nuevo;
    }
}

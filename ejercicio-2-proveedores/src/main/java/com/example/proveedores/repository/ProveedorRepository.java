package com.example.proveedores.repository;

import com.example.proveedores.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria: almacena los proveedores en una lista.
 */
@Repository
public class ProveedorRepository {

    private final List<Proveedor> proveedores = new CopyOnWriteArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(0);

    public Proveedor save(Proveedor proveedor) {
        proveedor.setId(secuencia.incrementAndGet());
        proveedores.add(proveedor);
        return proveedor;
    }

    public List<Proveedor> findAll() {
        return new ArrayList<>(proveedores);
    }

    public Optional<Proveedor> findById(Long id) {
        return proveedores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Proveedor> findByCategoria(String categoria) {
        return proveedores.stream()
                .filter(p -> p.getCategoria() != null
                        && p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }
}

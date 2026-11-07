package com.example.proveedores.exception;

public class ProveedorNotFoundException extends RuntimeException {

    public ProveedorNotFoundException(Long id) {
        super("Proveedor con id " + id + " no encontrado");
    }
}

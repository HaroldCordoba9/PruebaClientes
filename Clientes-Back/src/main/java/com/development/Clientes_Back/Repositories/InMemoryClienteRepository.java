package com.development.Clientes_Back.Repositories;

import java.util.ArrayList;
import java.util.List;

import com.development.Clientes_Back.Model.Entities.Cliente;

public class InMemoryClienteRepository {

    private List<Cliente> clientes;

    public InMemoryClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public void save(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
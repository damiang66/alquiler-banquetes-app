package com.damian.backen.usuarios.app.usuariosapp.service.implementacion;

import com.damian.backen.usuarios.app.usuariosapp.endidad.Cliente;
import com.damian.backen.usuarios.app.usuariosapp.repositorio.ClienteRepositorio;
import com.damian.backen.usuarios.app.usuariosapp.service.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClienteImplementacion implements ClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Override
    public List<Cliente> findAll() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepositorio.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @Override
    public void delete(Long id) {
clienteRepositorio.deleteById(id);
    }
}

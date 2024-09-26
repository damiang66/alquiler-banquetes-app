package com.damian.backen.usuarios.app.usuariosapp.controlador;

import com.damian.backen.usuarios.app.usuariosapp.endidad.Cliente;
import com.damian.backen.usuarios.app.usuariosapp.service.ClienteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/clientes")
public class ClienteControlador {
    @Autowired
    private ClienteServicio clienteServicio;
    private ResponseEntity<?>validar(BindingResult result){
        Map<String,Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errores.put(e.getField(), "El campo: " + e.getField() + " " + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
    @GetMapping
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok().body(clienteServicio.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById (@PathVariable Long id){
        Optional<Cliente> clienteOptional = clienteServicio.findById(id);
        Cliente cliente = null;
        if(clienteOptional.isPresent()){
            cliente = clienteOptional.get();
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> save (@Valid @RequestBody Cliente cliente, BindingResult result){
        if (result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteServicio.save(cliente));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>update (@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Cliente>clienteOptional = clienteServicio.findById(id);
        Cliente clienteDb = null;
        if(clienteOptional.isPresent()){
            clienteDb = clienteOptional.get();
            clienteDb.setNombre(cliente.getNombre());
            //todos los otros campos
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteServicio.save(clienteDb));
        }
        return ResponseEntity.notFound().build();
    }
@DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        clienteServicio.delete(id);
        return ResponseEntity.noContent().build();
}
}

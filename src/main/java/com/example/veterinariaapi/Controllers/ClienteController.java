package com.example.veterinariaapi.Controllers;

import com.example.veterinariaapi.Dtos.Cliente.ClienteResponseDTO;
import com.example.veterinariaapi.Dtos.Cliente.MascotaDTO;
import com.example.veterinariaapi.Dtos.Cliente.NewClienteRequestDTO;
import com.example.veterinariaapi.Dtos.Cliente.UpdateClienteRequestDTO;
import com.example.veterinariaapi.Entities.MascotaEntity;
import com.example.veterinariaapi.Models.Cliente;
import com.example.veterinariaapi.Services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/nombreAndtelefono")
    public ResponseEntity<ClienteResponseDTO> getClienteByNombreAndTelefono(@RequestParam String nombre,@RequestParam Long telefono){
        ClienteResponseDTO clienteResponseDTO = clienteService.getClienteByNombreAndTelefono(nombre, telefono);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Cliente> getClienteByDni(@PathVariable Long dni){
        Cliente cliente = clienteService.getClienteByDni(dni);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cliente-response/{dni}")
    public ResponseEntity<ClienteResponseDTO> getClienteResponseDTOById(@PathVariable Long dni){
        ClienteResponseDTO clienteResponseDTO = clienteService.getClienteResponseDTOBDni(dni);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<ClienteResponseDTO> saveCliente(@RequestBody @Valid NewClienteRequestDTO newClienteRequestDTO){
        ClienteResponseDTO clienteSaved = clienteService.saveCliente(newClienteRequestDTO);
        if (Objects.isNull(clienteSaved)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }else {
            return ResponseEntity.ok(clienteSaved);
        }
    }

    @PutMapping("/updateModel/{dni}")//realizado para practicar nomas.No usar porque expongo los datos del model
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long dni,@RequestBody Cliente cliente){
        Cliente clieteActualizado = clienteService.updateCliente(dni, cliente);
        return ResponseEntity.ok(clieteActualizado);
    }

    @PutMapping("updateEntity/clienteResponseDTO/{dni}")
    public ResponseEntity<UpdateClienteRequestDTO> updateClienteResponseDTOResponseEntity(@PathVariable Long dni, @RequestBody UpdateClienteRequestDTO updateClienteRequestDTO){
        UpdateClienteRequestDTO clienteActualizado = clienteService.updateClienteDTO(dni, updateClienteRequestDTO);
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{dni}/mascotas")
    public ResponseEntity<List<MascotaDTO>> getMascotasByClienteDni(@PathVariable Long dni) {
        List<MascotaDTO> mascotasDTO = clienteService.getMascotasByClienteDni(dni);
        return new ResponseEntity<>(mascotasDTO, HttpStatus.OK);
    }
}

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

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cliente-response/{id}")
    public ResponseEntity<ClienteResponseDTO> getClienteResponseDTOById(@PathVariable Long id){
        ClienteResponseDTO clienteResponseDTO = clienteService.getClienteResponseDTOById(id);
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

    @PutMapping("/updateModel/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id,@RequestBody Cliente cliente){
        Cliente clieteActualizado = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(clieteActualizado);
    }

    @PutMapping("updateEntity/clienteResponseDTO/{id}")
    public ResponseEntity<UpdateClienteRequestDTO> updateClienteResponseDTOResponseEntity(@PathVariable Long id, @RequestBody Cliente cliente){
        UpdateClienteRequestDTO clienteActualizado = clienteService.updateClienteDTO(id, cliente);
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

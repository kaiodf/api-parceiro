package br.com.kaio.petz.controller;

import br.com.kaio.petz.dto.ClienteDto;
import br.com.kaio.petz.model.Cliente;
import br.com.kaio.petz.service.ClienteService;
import br.com.kaio.petz.util.Conversor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping({"/cliente"})
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    Conversor conversor;

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ClienteDto> consultar(@PathVariable("id") Long id){
        ClienteDto dto = clienteService.find(id);
        if(Objects.isNull(dto.getCpf())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> find(){
        List<ClienteDto> list = clienteService.findAll();
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Cliente> create(@RequestBody ClienteDto clienteDto) {
        try {

            Cliente cliente = conversor.loadCliente(clienteDto);

            var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(cliente.getCpf()).build().toUri();

            clienteService.save(cliente);
            return ResponseEntity.created(uri).body(cliente);

        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        if(clienteService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/cliente/{id}", produces = { "application/json" })
    public ResponseEntity<ClienteDto> update(@PathVariable("id") long id, @RequestBody ClienteDto clienteDto) {
        try {
            Optional<Cliente> optional = clienteService.findCliente(id);
            if(!optional.isPresent()){
                return ResponseEntity.notFound().build();
            }else {
                clienteService.update(clienteDto, optional.get());
                return ResponseEntity.ok(clienteDto);
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }


}

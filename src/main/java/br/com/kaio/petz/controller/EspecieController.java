package br.com.kaio.petz.controller;

import br.com.kaio.petz.dto.ClienteDto;
import br.com.kaio.petz.dto.EspecieDto;
import br.com.kaio.petz.model.Cliente;
import br.com.kaio.petz.model.Especie;
import br.com.kaio.petz.service.EspecieService;
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
@RequestMapping({"/especie"})
public class EspecieController {

    @Autowired
    EspecieService especieService;

    @Autowired
    Conversor conversor;

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<EspecieDto> consultar(@PathVariable("id") Long id){
        EspecieDto dto = especieService.find(id);
        if(Objects.isNull(dto.getNome())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<EspecieDto>> find(){
        List<EspecieDto> list = especieService.findAll();
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Especie> create(@RequestBody EspecieDto especieDto) {
        try {

            Especie especie = conversor.loadEspecie(especieDto);
            var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(especie.getDesc_especie()).build().toUri();
            especieService.save(especie);
            return ResponseEntity.created(uri).body(especie);

        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        if(especieService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/cliente/{id}", produces = { "application/json" })
    public ResponseEntity<EspecieDto> update(@PathVariable("id") long id, @RequestBody EspecieDto especieDto) {
        try {
            Optional<Especie> optional = especieService.findEspecie(id);
            if(!optional.isPresent()){
                return ResponseEntity.notFound().build();
            }else {
                especieService.update(especieDto, optional.get());
                return ResponseEntity.ok(especieDto);
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

}

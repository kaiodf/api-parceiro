package br.com.kaio.petz.controller;

import br.com.kaio.petz.dto.PetDto;
import br.com.kaio.petz.model.Pet;
import br.com.kaio.petz.service.PetService;
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
@RequestMapping({"/pet"})
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    Conversor conversor;

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<PetDto> consultar(@PathVariable("id") Long id){
        PetDto dto = petService.find(id);
        if(Objects.isNull(dto.getNome())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> find(){
        List<PetDto> list = petService.findAll();
        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Pet> create(@RequestBody PetDto petDto) {
        try {

            Pet pet = conversor.loadPet(petDto);
            var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(pet.getNome_pet()).build().toUri();
            petService.save(pet);
            return ResponseEntity.created(uri).body(pet);

        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        if(petService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/cliente/{id}", produces = { "application/json" })
    public ResponseEntity<PetDto> update(@PathVariable("id") long id, @RequestBody PetDto petDto) {
        try {
            Optional<Pet> optional = petService.findEspecie(id);
            if(!optional.isPresent()){
                return ResponseEntity.notFound().build();
            }else {
                petService.update(petDto, optional.get());
                return ResponseEntity.ok(petDto);
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

}

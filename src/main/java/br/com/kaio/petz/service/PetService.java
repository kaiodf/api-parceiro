package br.com.kaio.petz.service;

import br.com.kaio.petz.dto.PetDto;
import br.com.kaio.petz.model.Pet;
import br.com.kaio.petz.repository.PetRepository;
import br.com.kaio.petz.util.Conversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    Conversor conversor;


    public PetDto find(Long id) {
        Optional<Pet> op = petRepository.findById(id);
        return op.map(c -> conversor.loadPetDto(c)).orElse(PetDto.builder().build());
    }

    public void save(Pet pet){
        petRepository.save(pet);
    }

    public List<PetDto> findAll() {
        List<Pet> pets = petRepository.findAll();
        return conversor.loadListPets(pets);
    }

    public void update(PetDto petDto, Pet pet) {
        this.save(conversor.updatePet(pet,petDto));
    }

    public Optional<Pet> findEspecie(long id) {
        return petRepository.findById(id);
    }

    public boolean delete(Long id) {
        Optional<Pet> op = findEspecie(id);
        if(op.isPresent()){
            petRepository.delete(op.get());
            return true;
        }
        return false;
    }
}

package br.com.kaio.petz.repository;

import br.com.kaio.petz.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository  extends JpaRepository<Pet, Long> {
}

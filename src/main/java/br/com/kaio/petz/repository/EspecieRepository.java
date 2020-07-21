package br.com.kaio.petz.repository;

import br.com.kaio.petz.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepository  extends JpaRepository<Especie, Long> {
}

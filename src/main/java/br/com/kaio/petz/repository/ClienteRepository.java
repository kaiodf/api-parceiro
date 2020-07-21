package br.com.kaio.petz.repository;

import br.com.kaio.petz.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    @Query("SELECT c FROM DBO.CLIENTE c WHERE c.id_cliente = :id")
//    Cliente find(@Param("id") Long id);
}

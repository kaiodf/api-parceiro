package br.com.kaio.petz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@Table(schema = "dbo", name="pet")
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pet_sequence")
    @SequenceGenerator(name="pet_sequence", sequenceName="dbo.id_pet_seq")
    private Long id_pet;
    private String nome_pet;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;

    @ManyToMany
    @JoinTable(schema = "dbo", name="cliente_pet", joinColumns=
            {@JoinColumn(name="id_pet")}, inverseJoinColumns=
            {@JoinColumn(name="id_cliente")})
    private List<Cliente> clientes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie")
    private Especie especie;
}

package br.com.kaio.petz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(schema = "dbo", name = "cliente")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_sequence")
    @SequenceGenerator(name="cliente_sequence", sequenceName="dbo.id_cliente_seq")
    private Long id_cliente;
    private String nome_cliente;
    private String cpf;
    private String whatsapp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sexo")
    private Sexo sexo;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(schema = "dbo", name="cliente_pet", joinColumns=
            {@JoinColumn(name="id_cliente")}, inverseJoinColumns=
            {@JoinColumn(name="id_pet")})
    private List<Pet> pets;
}

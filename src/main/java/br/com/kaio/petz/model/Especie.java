package br.com.kaio.petz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(schema = "dbo", name = "especie")
@AllArgsConstructor
@NoArgsConstructor
public class Especie {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="especie_sequence")
    @SequenceGenerator(name="especie_sequence", sequenceName="dbo.id_especie_seq")
    private Long id_especie;
    private String desc_especie;
}

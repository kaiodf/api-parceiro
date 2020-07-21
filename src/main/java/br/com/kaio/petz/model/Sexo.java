package br.com.kaio.petz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "dbo", name = "sexo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sexo {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sexo_sequence")
    @SequenceGenerator(name="sexo_sequence", sequenceName="dbo.id_sexo_seq")
    private Long id_sexo;
    private String desc_sexo;
}

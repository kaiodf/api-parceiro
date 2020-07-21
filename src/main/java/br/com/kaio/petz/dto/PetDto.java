package br.com.kaio.petz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDto implements Serializable {

    private String nome;
    private Date dataNascimento;
    private String sexo;
    private String especie;
}

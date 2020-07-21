package br.com.kaio.petz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto implements Serializable {
    private String nome;
    private String cpf;
    private String whatsapp;
    private String sexo;
    private List<PetDto> pets;
}

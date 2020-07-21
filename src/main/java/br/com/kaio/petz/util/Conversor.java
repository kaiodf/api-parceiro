package br.com.kaio.petz.util;

import br.com.kaio.petz.dto.ClienteDto;
import br.com.kaio.petz.dto.EspecieDto;
import br.com.kaio.petz.dto.PetDto;
import br.com.kaio.petz.enumm.EspecieEnum;
import br.com.kaio.petz.enumm.SexoEnum;
import br.com.kaio.petz.model.Cliente;
import br.com.kaio.petz.model.Especie;
import br.com.kaio.petz.model.Pet;
import br.com.kaio.petz.model.Sexo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Conversor {
    public Cliente loadCliente(ClienteDto clienteDto){
        return Cliente.builder()
                .cpf(clienteDto.getCpf())
                .nome_cliente(clienteDto.getNome())
                .whatsapp(clienteDto.getWhatsapp())
                .sexo(loadSexo(clienteDto.getSexo()))
                .pets(loadPets(clienteDto.getPets()))
                .build();
    }

    public List<Pet> loadPets(List<PetDto> pets) {
        List<Pet> petList = new ArrayList<>();
        for (PetDto dto: pets) {
            petList.add(Pet.builder()
                    .dataNascimento(dto.getDataNascimento())
                    .especie(loadEspecie(dto.getEspecie()))
                    .sexo(loadSexo(dto.getSexo()))
                    .nome_pet(dto.getNome())
                    .build());

        }
        return petList;
    }

    public Especie loadEspecie(EspecieDto especieDto) {
        return Especie.builder().desc_especie(especieDto.getNome()).build();
    }

    public Especie loadEspecie(String especie) {
        if(EspecieEnum.CANINA.getVal().equalsIgnoreCase(especie)){
            return Especie.builder().id_especie(1L).build();
        }else{
            return Especie.builder().id_especie(2L).build();
        }
    }

    public Sexo loadSexo(String sexo) {
        if(SexoEnum.MASCULINO.getVal().equalsIgnoreCase(sexo)) {
            return Sexo.builder().id_sexo(1L).build();
        }else{
            return Sexo.builder().id_sexo(2L).build();
        }
    }

    public ClienteDto loadClienteDto(Cliente cliente) {
        return ClienteDto.builder()
                .nome(cliente.getNome_cliente())
                .cpf(cliente.getCpf())
                .whatsapp(cliente.getWhatsapp())
                .sexo(cliente.getSexo().getDesc_sexo())
                .pets(loadPetsDto(cliente.getPets())).build();
    }

    public List<PetDto> loadPetsDto(List<Pet> pets) {
        List<PetDto> list = new ArrayList<>();
        for (Pet pet:pets) {
            list.add(PetDto.builder()
                    .nome(pet.getNome_pet())
                    .dataNascimento(pet.getDataNascimento())
                    .especie(pet.getEspecie().getDesc_especie())
                    .sexo(pet.getSexo().getDesc_sexo()).build());
        }
        return list;
    }

    public List<ClienteDto> loadListClienteDto(List<Cliente> list) {
        List<ClienteDto> dtoList = new ArrayList<>();
        for (Cliente c:list) {
            dtoList.add(loadClienteDto(c));
        }

        return dtoList;
    }

    public EspecieDto loadEspecieDto(Especie especie) {
        return EspecieDto.builder().nome(especie.getDesc_especie()).build();
    }

    public PetDto loadPetDto(Pet pet) {
        return PetDto.builder()
                .sexo(pet.getSexo().getDesc_sexo())
                .especie(pet.getEspecie().getDesc_especie())
                .dataNascimento(pet.getDataNascimento())
                .nome(pet.getNome_pet())
                .build();
    }

    public Cliente updateCliente(Cliente cliente, ClienteDto dto) {
        cliente.setCpf(dto.getCpf());
        cliente.setNome_cliente(dto.getNome());
        cliente.setWhatsapp(dto.getWhatsapp());
        cliente.setSexo(loadSexo(dto.getSexo()));
        cliente.setPets(loadPets(dto.getPets()));
        return cliente;

    }

    public List<EspecieDto> loadListEspecieDto(List<Especie> listEsp) {
        List<EspecieDto> list = new ArrayList<>();
        for (Especie especie: listEsp) {
            list.add(EspecieDto.builder().nome(especie.getDesc_especie()).build());
        }
        return list;
    }

    public Especie updateEspecie(Especie especie, EspecieDto especieDto) {
        especie.setDesc_especie(especieDto.getNome());
        return especie;
    }

    public Pet loadPet(PetDto petDto) {
        return Pet.builder()
                .nome_pet(petDto.getNome())
                .sexo(loadSexo(petDto.getSexo()))
                .especie(loadEspecie(petDto.getEspecie()))
                .dataNascimento(petDto.getDataNascimento())
                .build();
    }

    public List<PetDto> loadListPets(List<Pet> pets) {
        List<PetDto> list = new ArrayList<>();
        for (Pet pet: pets) {
            list.add(PetDto.builder()
                    .nome(pet.getNome_pet())
                    .dataNascimento(pet.getDataNascimento())
                    .especie(pet.getEspecie().getDesc_especie())
                    .sexo(pet.getSexo().getDesc_sexo())
                    .build());
        }
        return list;
    }

    public Pet updatePet(Pet pet, PetDto petDto) {
        pet.setDataNascimento(petDto.getDataNascimento());
        pet.setEspecie(loadEspecie(petDto.getEspecie()));
        pet.setNome_pet(petDto.getNome());
        pet.setSexo(loadSexo(petDto.getSexo()));
//        pet.setClientes(loadListCliente(petDto.get));
        return pet;
    }
}

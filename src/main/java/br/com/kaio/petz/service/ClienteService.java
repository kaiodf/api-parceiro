package br.com.kaio.petz.service;

import br.com.kaio.petz.dto.ClienteDto;
import br.com.kaio.petz.model.Cliente;
import br.com.kaio.petz.model.Pet;
import br.com.kaio.petz.repository.ClienteRepository;
import br.com.kaio.petz.repository.PetRepository;
import br.com.kaio.petz.util.Conversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    Conversor conversor;

    public ClienteDto find(Long id){
        Optional<Cliente> op = clienteRepository.findById(id);
        return op.map(c -> conversor.loadClienteDto(c)).orElse(ClienteDto.builder().build());

    }

    public List<ClienteDto> findAll() {
        List<Cliente> list = clienteRepository.findAll();

        return conversor.loadListClienteDto(list);
    }

    public void save(Cliente cliente) {
        for (Pet pet: cliente.getPets()) {
            petRepository.save(pet);
        }
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> findCliente(long id) {
        return clienteRepository.findById(id);
    }

    public void update(ClienteDto clienteDto, Cliente cliente) {
        this.save(conversor.updateCliente(cliente, clienteDto));

    }

    public boolean delete(Long id) {
        Optional<Cliente> optional = findCliente(id);
        if(optional.isPresent()) {
            clienteRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}

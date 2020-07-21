package br.com.kaio.petz.service;

import br.com.kaio.petz.dto.EspecieDto;
import br.com.kaio.petz.model.Especie;
import br.com.kaio.petz.repository.EspecieRepository;
import br.com.kaio.petz.util.Conversor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecieService {

    @Autowired
    EspecieRepository especieRepository;

    @Autowired
    Conversor conversor;

    public EspecieDto find(Long id) {
        Optional<Especie> op = especieRepository.findById(id);
        return op.map(c -> conversor.loadEspecieDto(c)).orElse(EspecieDto.builder().build());
    }

    public void save(Especie especie) {
        especieRepository.save(especie);
    }

    public List<EspecieDto> findAll() {
        List<Especie> list = especieRepository.findAll();
        return conversor.loadListEspecieDto(list);
    }

    public boolean delete(Long id) {
        Optional<Especie> op = findEspecie(id);
        if(op.isPresent()){
            especieRepository.delete(op.get());
            return true;
        }
        return false;

    }

    public Optional<Especie> findEspecie(Long id) {
        return especieRepository.findById(id);
    }

    public void update(EspecieDto especieDto, Especie especie) {
        this.save(conversor.updateEspecie(especie,especieDto));
    }
}

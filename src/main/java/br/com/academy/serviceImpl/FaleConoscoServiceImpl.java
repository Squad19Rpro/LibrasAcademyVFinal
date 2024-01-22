package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dto.FaleConoscoDTO;
import br.com.academy.entidades.FaleConosco;
import br.com.academy.repository.FaleRepository;
import br.com.academy.service.FaleConoscoService;

@Service
public class FaleConoscoServiceImpl implements FaleConoscoService{
    @Autowired
    private FaleRepository faleRepository;

	@Override
	public void save(FaleConoscoDTO faleConoscoDTO) {
		FaleConosco faleConosco = new FaleConosco(faleConoscoDTO);
        faleRepository.save(faleConosco);	
	}

	@Override
	public FaleConoscoDTO findById(Long id) {
        return new FaleConoscoDTO(faleRepository.findById(id).get());
	}

	@Override
	public List<FaleConoscoDTO> findAll() {
        List<FaleConosco> faleConoscos = faleRepository.findAll();
        return faleConoscos.stream().map(FaleConoscoDTO::new).toList();
	}

	@Override
	public FaleConoscoDTO update(FaleConoscoDTO faleConoscoUpdate, FaleConoscoDTO faleConoscoAlvo) throws Exception {
        faleConoscoUpdate.setNome_msg(faleConoscoUpdate.getNome_msg() != null ? faleConoscoUpdate.getNome_msg() : faleConoscoAlvo.getNome_msg());
        faleConoscoUpdate.setEmail_msg(faleConoscoUpdate.getEmail_msg() != null ? faleConoscoUpdate.getEmail_msg() : faleConoscoAlvo.getEmail_msg());
        faleConoscoUpdate.setAssunto(faleConoscoUpdate.getAssunto() != null ? faleConoscoUpdate.getAssunto() : faleConoscoAlvo.getAssunto());
        faleConoscoUpdate.setMensagem(faleConoscoUpdate.getMensagem() != null ? faleConoscoUpdate.getMensagem() : faleConoscoAlvo.getMensagem());

        FaleConosco faleConosco = new FaleConosco(faleConoscoUpdate);
        return new FaleConoscoDTO(faleRepository.save(faleConosco));
	}

	@Override
	public void deleteById(Long id) {
        Optional<FaleConosco> optionalFaleConosco = faleRepository.findById(id);

        if (optionalFaleConosco.isPresent()) {
            // A mensagem foi encontrado, pode deletar
        	FaleConosco faleConosco = optionalFaleConosco.get();
        	faleRepository.delete(faleConosco);
        } else {
            // FaleConosco não encontrado, lançar exceção ou lidar de outra forma
            throw new IllegalArgumentException("Mensagem não encontrada");
        }
	}
}

package floristeria.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import floristeria.Model.Flor;
import floristeria.Repository.FlorRepository;

@Service    
public class FlorService {
    private final FlorRepository florRepository;

    public FlorService(FlorRepository florRepository){
        this.florRepository = florRepository;
    }

    public List<Flor> listarTodas(){
        return florRepository.findAll(); //listar todas las flores
    }

    public Flor save(Flor flor){
        return florRepository.save(flor); //gruardar una flor (nuevo o existente)
    }

    public void deleteById(Long id) {
        florRepository.deleteById(id); //eliminar la flor por ID
    }

    public Flor findById(Long id) {
        return florRepository.findById(id).orElse(null); // Busca una flor por ID, devuelve null si no existe
    }

    public Flor update(Flor flor) {
        // Verifica si la flor existe antes de actualizar
        if (flor.getId() != null && florRepository.existsById(flor.getId())) {
            return florRepository.save(flor); // Actualiza la flor si existe
        }
        return null; // Devuelve null si la flor no existe
    }
}

    


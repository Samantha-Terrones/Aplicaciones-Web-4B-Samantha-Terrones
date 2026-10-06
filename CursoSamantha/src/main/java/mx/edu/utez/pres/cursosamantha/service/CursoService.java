package mx.edu.utez.pres.cursosamantha.service;


import mx.edu.utez.pres.cursosamantha.model.Curso;
import mx.edu.utez.pres.cursosamantha.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    private final CursoRepository repo;

    public CursoService(CursoRepository repo) {
        this.repo = repo;
    }

    public List<Curso> getAll() {
        return repo.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return repo.findById(id);
    }

    public Curso save(Curso curso) {
        return repo.save(curso);
    }

    public Optional<Curso> update(Long id, Curso curso) {
        Optional<Curso> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Curso db = optional.get();
        db.setName(curso.getName());
        db.setMax(curso.getMax());
        db.setPrice(curso.getPrice());

        return Optional.of(repo.save(db));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
package mx.edu.utez.pres.cursosamantha.repository;



import mx.edu.utez.pres.cursosamantha.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
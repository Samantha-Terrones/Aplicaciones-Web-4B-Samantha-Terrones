package mx.edu.utez.pres.cursosamantha.repository;


import mx.edu.utez.pres.cursosamantha.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
}
package mx.edu.utez.pres.tiendaproductos.repository;

import mx.edu.utez.pres.tiendaproductos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

package mx.edu.utez.pres.tiendaproductos.repository;

import mx.edu.utez.pres.tiendaproductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Producto, Long> {
}

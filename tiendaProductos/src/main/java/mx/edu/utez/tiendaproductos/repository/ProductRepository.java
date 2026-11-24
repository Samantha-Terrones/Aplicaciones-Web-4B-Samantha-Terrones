package mx.edu.utez.tiendaproductos.repository;

import mx.edu.utez.tiendaproductos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Producto,Long> {

}

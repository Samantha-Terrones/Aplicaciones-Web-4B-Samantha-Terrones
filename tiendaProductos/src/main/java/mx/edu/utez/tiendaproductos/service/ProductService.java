package mx.edu.utez.tiendaproductos.service;

import mx.edu.utez.tiendaproductos.excepcion.BussinessException;
import mx.edu.utez.tiendaproductos.excepcion.ResourceNotFoundException;
import mx.edu.utez.tiendaproductos.model.Category;
import mx.edu.utez.tiendaproductos.model.Producto;
import mx.edu.utez.tiendaproductos.repository.CategoryRepository;
import mx.edu.utez.tiendaproductos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final CategoryRepository repoCategory;

    public ProductService(ProductRepository repo, CategoryRepository categoryRepository) {
        this.repo = repo;
        this.repoCategory = categoryRepository;
    }

    public List<Producto> getAll() {
        return repo.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return repo.findById(id);
    }

    public Producto save(Producto producto, Long categoryId) {
        Category category = repoCategory.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found whit id:" + categoryId)
        );//Arroja un error si no encuentra algo de
        //Categoria evitando que se crea un producto si no se encuentra categoria
        producto.setCategory(category);
        return repo.save(producto);

    }

    public Producto update(long id, Producto producto, Long categoryId) {

        Producto productDb = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id" + id)
        );
        Category category = repoCategory.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found with id" + categoryId)
        );
        //Producto productDb = opcional.get();

        productDb.setName(producto.getName());
        productDb.setPrice(producto.getPrice());
        productDb.setStock(producto.getStock());
        productDb.setCategory(category);

        return repo.save(productDb);
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public String search(Long id, int cantidad, int tipo) {
        Producto prod = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id:" + id)
        );
        if (tipo==1) {
            prod.setStock(prod.getStock() + cantidad);
            repo.save(prod);
        } else if (tipo==2) {
            if (prod.getStock() < cantidad) {
                throw new BussinessException("no tengo suficiente stock");
                //return true;
            } else {
                prod.setStock(prod.getStock() - cantidad);
                repo.save(prod);
            }
            return "Producto guardado";
        }
        return "Producto no guardado";

    }

}

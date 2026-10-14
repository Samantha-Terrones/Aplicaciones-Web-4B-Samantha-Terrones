package mx.edu.utez.pres.tiendaproductos.service;


import mx.edu.utez.pres.tiendaproductos.model.Category;
import mx.edu.utez.pres.tiendaproductos.model.Producto;
import mx.edu.utez.pres.tiendaproductos.repository.CategoryRepository;
import mx.edu.utez.pres.tiendaproductos.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductRepository repo;
    private final CategoryRepository repoCategory;

    public ProductoService(ProductRepository repo, CategoryRepository repoCategory){
        this.repo=repo;
        this.repoCategory=repoCategory;
    }

    public List<Producto> getAll(){
        return repo.findAll();
    }

    public Optional<Producto> findById(Long id){
        return repo.findById(id);
    }

    public Producto save(Producto producto,Long categoryId){
        Category category= repoCategory.findById(categoryId).orElseThrow();//Arrojar un error cuando no encuentre un Optional para category, arroja un error evitando que el producte se cree
        producto.setCategory(category);
        return repo.save(producto);
    }

    public Optional<Producto> update(long id, Producto producto){
        Optional<Producto> optional= repo.findById(id);
        if(optional.isEmpty()){
            return optional.empty();
        }
        Producto productDb= optional.get();

        productDb.setName(producto.getName());
        productDb.setPrice(producto.getPrice());
        productDb.setStock(producto.getStock());

        return Optional.of(repo.save(productDb));
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}

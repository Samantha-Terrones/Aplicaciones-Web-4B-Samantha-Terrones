package mx.edu.utez.pres.tiendaproductos.service;


import mx.edu.utez.pres.tiendaproductos.exception.ResourceNotFoundException;
import mx.edu.utez.pres.tiendaproductos.model.Category;
import mx.edu.utez.pres.tiendaproductos.model.Producto;
import mx.edu.utez.pres.tiendaproductos.repository.CategoryRepository;
import mx.edu.utez.pres.tiendaproductos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.lang.module.ResolutionException;
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
        Category category= repoCategory.findById(categoryId).orElseThrow(
                ()->
                    new ResourceNotFoundException("Category not found with id"+categoryId)
        );//Arrojar un error cuando no encuentre un Optional para category, arroja un error evitando que el producte se cree
        producto.setCategory(category);
        return repo.save(producto);
    }

    public Optional<Producto> update(long id, Producto producto,Long categoryId){

        Producto productDB= repo.findById(id).orElseThrow(
                ()->
                        new ResourceNotFoundException("Product not found with id"+id)
        );

        Category category= repoCategory.findById(categoryId).orElseThrow(
                ()->
                        new ResourceNotFoundException("Category not found with id"+categoryId)
        );
        //Producto productDb= optional.get();


        productDB.setName(producto.getName());
        productDB.setPrice(producto.getPrice());
        productDB.setStock(producto.getStock());
        productDB.setCategory(category);

        return Optional.of(repo.save(productDB));
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}

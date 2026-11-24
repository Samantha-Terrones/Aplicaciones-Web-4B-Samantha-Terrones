package mx.edu.utez.tiendaproductos.service;

import mx.edu.utez.tiendaproductos.excepcion.ResourceNotFoundException;
import mx.edu.utez.tiendaproductos.model.Category;
import mx.edu.utez.tiendaproductos.model.Producto;
import mx.edu.utez.tiendaproductos.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repo;


    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> getAll() {
        return repo.findAll();
    }
    public Optional<Category> getById(Long id) {
        return repo.findById(id);
    }

    public Category save(Category newCategory){
        return repo.save(newCategory);
    }

    public boolean delete(Long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    public Optional<Category> update(Long id,Category updateCategory){
        Category categoriaUpdate= repo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id:"+updateCategory.getId())
        );
        categoriaUpdate.setName(updateCategory.getName());
        return Optional.of(repo.save(categoriaUpdate));
    }


}

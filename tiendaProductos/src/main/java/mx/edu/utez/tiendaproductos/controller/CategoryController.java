package mx.edu.utez.tiendaproductos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.tiendaproductos.dto.CategoryRequest;
import mx.edu.utez.tiendaproductos.dto.CategoryResponse;
import mx.edu.utez.tiendaproductos.model.Category;
import mx.edu.utez.tiendaproductos.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    public Category toEntity(CategoryRequest category) {
        Category entity = new Category();
        entity.setName(category.name());
        return entity;
    }
    public CategoryResponse toResponse(Category entity){

        return new CategoryResponse(entity.getId(), entity.getName()) ;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<CategoryResponse> list= new ArrayList<>();
        for(Category c : service.getAll()){
            list.add(toResponse(c));
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        Optional<Category> search= service.getById(id);
        if(search.isPresent()){
            return ResponseEntity.ok(toResponse(search.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest category){
        Category entity = service.save(toEntity(category));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,@Valid @RequestBody CategoryRequest category){
        Optional<Category> update= service.update(id,toEntity(category));
        if (update.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(update.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean exist= service.delete(id);
        if (exist){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }

}

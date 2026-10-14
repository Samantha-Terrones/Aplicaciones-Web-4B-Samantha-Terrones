package mx.edu.utez.pres.tiendaproductos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.pres.tiendaproductos.dto.ProductRequest;
import mx.edu.utez.pres.tiendaproductos.dto.ProductResponse;
import mx.edu.utez.pres.tiendaproductos.model.Producto;
import mx.edu.utez.pres.tiendaproductos.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductoService service;

    public ProductController(ProductoService service){
        this.service=service;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> listResponse = new ArrayList<>();
        for ( Producto p: service.getAll()) {
            listResponse.add(toResponse(p));
        }
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
        Optional<Producto> producto= service.findById(id);
        if(producto.isPresent()){
            return ResponseEntity.ok(toResponse(producto.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> sale(@Valid @RequestBody ProductRequest producto){
        Producto productSaved= service.save(toEntity(producto),producto.categoryId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse((productSaved)));
        /*if(producto.name().isEmpty()){
            ResponseEntity.badRequest().body("ERROR el no esta el nombre");
        }*/
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        boolean deleted= service.delete(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest producto){
        Optional<Producto> optional= service.update(id,toEntity(producto));
        if(optional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private ProductResponse toResponse(Producto product){
        return new ProductResponse(product.getName(), product.getId(), product.getPrice(), product.getStock(),  0L,"");
    }

    private Producto toEntity(ProductRequest request){
        Producto producto= new Producto();
        producto.setName(request.name());
        producto.setPrice(request.price());
        producto.setStock(request.stock());
        return producto;
    }

}

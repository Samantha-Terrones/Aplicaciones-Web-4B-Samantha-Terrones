package mx.edu.utez.tiendaproductos.controller;

import jakarta.validation.Valid;
import mx.edu.utez.tiendaproductos.dto.ProductResponse;
import mx.edu.utez.tiendaproductos.dto.ProductoRequest;
import mx.edu.utez.tiendaproductos.dto.StockRequest;
import mx.edu.utez.tiendaproductos.excepcion.ResourceNotFoundException;
import mx.edu.utez.tiendaproductos.model.Producto;
import mx.edu.utez.tiendaproductos.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//regresar el ProductResponse por buenas practicas
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service){
        this.service=service;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> listResponse=new ArrayList<>();
        for(Producto p: service.getAll()){
            listResponse.add(toResponse(p));
        }
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
        Producto product=service.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product not found with id:"+id)
        );//En esta parte no es un objeto de tipo producto
        return ResponseEntity.ok(toResponse(product));
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductoRequest producto){
        /*if(producto.name().isEmpty()){
            ResponseEntity.badRequest().body("ERROR el no esta el nombre");
        }*/
        Producto save= service.save(toEntity(producto),producto.categoryId()); //save recibe el producto y atraves del de service.save con toEntity de producto lo guardamos empatando los tipos de datos
        return  ResponseEntity.status(HttpStatus.CREATED).body(toResponse(save));
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
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,@Valid @RequestBody ProductoRequest producto){
        Producto optional= service.update(id,toEntity(producto),producto.categoryId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(optional));
    }

    private ProductResponse toResponse(Producto product){
        return new ProductResponse(product.getId(),product.getName(),product.getPrice(),product.getStock(),product.getCategory().getId(),product.getCategory().getName());
    }

    private Producto toEntity(ProductoRequest req){
        Producto product= new Producto();
        product.setName(req.name());
        product.setPrice(req.price());
        product.setStock(req.stock());
        return product;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProductResponse> removeStock(@PathVariable Long id, @RequestBody StockRequest stock){
        String search= service.search(id,stock.cantidad(),stock.tipo());

        if (search.equals("Producto guardado")){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().build();
    }

}

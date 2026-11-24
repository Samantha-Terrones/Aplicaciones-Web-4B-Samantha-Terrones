package mx.edu.utez.tiendaproductos.dto;

import mx.edu.utez.tiendaproductos.model.Category;

public record ProductResponse(
        Long id,
        String name,
        double price,
        int stock,
        Long categoryId,
        String categoryName) {
}

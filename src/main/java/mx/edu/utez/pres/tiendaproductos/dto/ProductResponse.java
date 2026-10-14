package mx.edu.utez.pres.tiendaproductos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductResponse(
        @NotBlank(message = "Invalid Name")
        String name,
        Long id,
        @PositiveOrZero
        double price,
        @Positive
        int stock,
        Long categoryId,
        String categoryName
) {
}
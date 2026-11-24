package mx.edu.utez.tiendaproductos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import mx.edu.utez.tiendaproductos.model.Category;

public record ProductoRequest (
        @NotBlank(message="Name is mandatory")
        @NotNull(message = "Name can´t be empty")
        @Valid
        String name,
        @NotNull
        @PositiveOrZero
        Integer stock,
        @Positive //no acepta numeros negativos ni el cero
        double price,
        @NotNull
        Long categoryId
        ){

}

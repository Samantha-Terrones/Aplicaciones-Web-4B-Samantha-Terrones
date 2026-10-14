package mx.edu.utez.pres.tiendaproductos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record ProductRequest (
        @NotBlank(message="Name is mandatory")
        @NotNull(message = "")
        @Valid
        String name,
        @NotNull
        @PositiveOrZero
        Integer stock,
        @Positive //no acepta numeros negativos ni el cero
        double price,
        @NotNull(message = "ID cannot be null")
        Long categoryId


){

}
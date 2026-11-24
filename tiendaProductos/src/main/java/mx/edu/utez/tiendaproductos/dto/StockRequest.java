package mx.edu.utez.tiendaproductos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StockRequest(
        int cantidad,
        @NotBlank(message = "No puede ir vacio")
        @NotNull(message = "No puede ir vacio")
        @Min(value = 1,message = "Opciones validas 1 o 2")
        @Max(value = 2,message ="Opciones validas 1 o 2")
        int tipo
) {}

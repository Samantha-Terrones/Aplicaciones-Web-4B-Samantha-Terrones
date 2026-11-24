package mx.edu.utez.pres.tiendaproductos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StockRequest(
        @NotNull(message = "La cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser mayor a cero")
        Integer cantidad,

        @NotBlank(message = "La palabra clave es obligatoria")
        String action // Ejemplo: "add" o "remove"
) {
}
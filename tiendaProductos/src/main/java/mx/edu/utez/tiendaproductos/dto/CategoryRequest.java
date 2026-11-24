package mx.edu.utez.tiendaproductos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @NotBlank(message="Name is mandatory")
        @NotNull(message = "Name can´t be empty")
        @Valid
        String name
) {
}

package mx.edu.utez.pres.cursosamantha.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int max;
    private double price;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes;
}
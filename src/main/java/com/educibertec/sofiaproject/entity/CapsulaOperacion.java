package com.educibertec.sofiaproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_operaciones")
public class CapsulaOperacion implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long operaciones;
	private String proceso;
	private int tipo;
	@ManyToOne
	@JoinColumn(name = "producto")
	private CapsulaProducto producto;
	private int cantidad;
	private Double preciocpa;
	private LocalDate fechacpa;
	private int estado;
}

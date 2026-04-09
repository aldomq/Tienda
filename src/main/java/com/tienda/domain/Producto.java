    package com.tienda.domain;
    import java.math.BigDecimal;
    import jakarta.validation.constraints.DecimalMin;
    import jakarta.validation.constraints.Min;
    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;
    import java.io.Serializable;
    import lombok.Data;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.*;
    import java.io.Serializable;
    import java.math.BigDecimal;
    import lombok.Data;

    @Data
    @Entity
    @Table(name = "producto")
    public class Producto implements Serializable {

        // Se recomienda añadir un serialVersionUID
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_producto")
        private Integer  idProducto;
        //private Integer  idCategoria; ahora estra dentro del objeto categoria

        @Column(unique = true, nullable = false, length = 50)
        @NotNull
        @Size(max = 50)
        private String descripcion;
           private String detalle;

        @Column(precision = 12, scale=2)
        @NotNull(message="El precio no puede ser nulo")
        @DecimalMin(value="0.01", inclusive=true,message="El precio debe ser mayor o igual a 0")
             private BigDecimal precio;

          @NotNull(message="Las existencias no pueden ser nulo")
        @Min(value="0",message="Las existencias deben ser mayor o igual a 0")
    private Integer existencias;


        @Column(length = 1024)
        @Size(max = 1024)
        private String rutaImagen;

        @Column(name = "activo")
        private Boolean activo;     

    }
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
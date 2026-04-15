package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    public List<Producto> findByActivoTrue();

    public List<Producto> findByPrecioBetweenOrderByPrecioAsc(double precioInf, double precioSup);
    
    @Query(value="SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInfo and :precioSup")
    public List<Producto> consultaJPQL(double precioInf, double precioSup); 
    

    @Query(nativeQuery=true,
            value="SELECT p FROM producto p WHERE p.precio BETWEEN :precioInfo and :precioSup")
    public List<Producto> consultaSQL(double precioInf, double precioSup); 
    
    
}

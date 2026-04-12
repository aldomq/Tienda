package com.tienda.Controller;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;

    public IndexController(CategoriaService categoriaService, ProductoService productoService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
    }

    @GetMapping("/")
    public String listado(Model model) {
        var productos = productoService.getProductos(true);
        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);

        return "index";
    }

    @GetMapping("/consultas/{idCategoria}")
    public String listadoCategoria(@PathVariable("idCategoria") Integer idCategoria, Model model) {
        var categoriaOpt = categoriaService.getCategoria(idCategoria);

        if (categoriaOpt.isPresent()) {
            var categoria = categoriaOpt.get();
            model.addAttribute("productos", categoria.getProductos());
        } else {
            model.addAttribute("productos", Collections.emptyList());
        }

        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);

        return "index";
    }
}
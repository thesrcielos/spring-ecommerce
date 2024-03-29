package com.damd.demo.controller;

import com.damd.demo.model.Producto;
import com.damd.demo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    private final ProductoService productoService;

    public AdministratorController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("")
    public String home(Model model){
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos",productos);
        return "administrator/home";
    }

}

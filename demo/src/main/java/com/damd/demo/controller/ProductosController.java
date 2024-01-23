package com.damd.demo.controller;

import com.damd.demo.model.Producto;
import com.damd.demo.model.Usuario;
import com.damd.demo.service.ProductoService;
import com.damd.demo.service.UploadFileService;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/productos")
public class ProductosController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);
    private final ProductoService productoService;
    private final UploadFileService upload;
    
    ProductosController(ProductoService productoService, UploadFileService upload){
        this.productoService = productoService;
        this.upload = upload;
    }
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos",productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException{
        LOGGER.info("Este es el objeto producto {}",producto);
        Usuario usuario = new Usuario(1,"","","","","","","");
        producto.setUsuario(usuario);
        if(producto.getId() == null){
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoService.save(producto);
        return "redirect:/productos";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Optional<Producto> optionalProducto = productoService.get(id);
        Producto producto = optionalProducto.get();
        LOGGER.info("producto buscado {}",producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }
    
    @PutMapping("/update")
    public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException{
        Producto p = productoService.get(producto.getId()).get();
        if(file.isEmpty()){
            producto.setImagen(p.getImagen());
        }else{
            if(!p.getImagen().equals("default.jpg")){
                upload.deleteImagen(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoService.update(producto);
        return "redirect:/productos";
    }
    
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Producto p = productoService.get(id).get();
        if(!p.getImagen().equals("default.jpg")){
        upload.deleteImagen(p.getImagen());
        }
        productoService.delete(id);     
        return "redirect:/productos";
    }
}

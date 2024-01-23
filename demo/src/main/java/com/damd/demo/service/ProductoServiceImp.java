package com.damd.demo.service;

import com.damd.demo.model.Producto;
import com.damd.demo.repository.ProductRepository;

import java.util.Optional;

public class ProductoServiceImp implements ProductoService{

    private ProductRepository productoRepository;

    public ProductoServiceImp(ProductRepository productRepository) {
        this.productoRepository = productRepository;
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
}

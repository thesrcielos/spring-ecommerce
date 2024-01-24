package com.damd.demo.service;

import com.damd.demo.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public interface ProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();
}

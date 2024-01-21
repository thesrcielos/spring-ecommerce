package com.damd.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Orden(Integer id, String numero, LocalDateTime fechaCreacion, double total) {
}

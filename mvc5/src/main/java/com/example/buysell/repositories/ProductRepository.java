package com.example.buysell.repositories;

import com.example.buysell.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//JpaRepository предоставляет уже реализованные методы для выполнения различных операций сущностей в базе данных
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);//метод для посика по заголовку
}

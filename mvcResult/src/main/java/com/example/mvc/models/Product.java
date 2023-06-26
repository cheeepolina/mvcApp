package com.example.mvc.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ads")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String city;
    private String description;
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
    mappedBy = "product")//один объект к нескольким картинкам
    private List<Image> images = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)//один юзер ко многим объектам
    @JoinColumn
    private User user;

    private Long previewImageId;
    private LocalDateTime dateOfCreated;

    @PrePersist//помечаем метод который должен быть выполнен пеерд сохранением объекта вбд
    private void onCreate() { dateOfCreated = LocalDateTime.now(); }
//при каждом сохранении новой сущности поле dateOfCreated будет автоматически заполняться текущей датой и временем

    public void addImageToProduct(Image image) {
        image.setProduct(this);//данная строка устанавливает связь между объектом Image и текущим объектом
        images.add(image);//добавляем объект image в коллекцию images текущего объекта Product
    }
}

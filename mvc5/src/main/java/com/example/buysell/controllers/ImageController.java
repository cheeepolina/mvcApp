package com.example.buysell.controllers;

import com.example.buysell.models.Image;
import com.example.buysell.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")//возвращает картинку
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        //поиск изображения
        Image image = imageRepository.findById(id).orElse(null);//находим картинку по id и присваеваем image
        return ResponseEntity.ok()//Создается объект ResponseEntity с http статусом 200
                .header("fileName", image.getOriginalFileName())//утсанавливаем заголовок "fileName" с именем исходного файла изображения
                .contentType(MediaType.valueOf(image.getContentType()))//устанавливаем тип содержимого
                .contentLength(image.getSize())//длина
                //тело ответа
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }//которое представляет собой поток InputStreamResource из массива байтов изображения image.getBytes()
}

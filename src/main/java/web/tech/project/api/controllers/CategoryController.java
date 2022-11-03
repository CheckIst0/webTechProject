package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.errorhandlers.IdNotFoundException;
import web.tech.project.api.core.model.CategoryDto;
import web.tech.project.api.core.service.CategoryService;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    @Operation(summary = "Получение списка всех категорий")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PatchMapping("/setImage/{id}")
    @Operation(summary = "Обновление(изменение) картинки для категории с указанным ID")
    public String setImageToCategory(@RequestBody byte[] image, @PathVariable Long id) {
        try {
            return categoryService.setImageToCategory(image, id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }

    }

    @PostMapping("/add")
    @Operation(summary = "Добавление новой категории")
    public void addCategory(@RequestBody CategoryDto categoryDto, @RequestParam String source) throws IOException {
        File file = new File("C:\\Users\\Чингиз\\Desktop\\учеба\\Web-технологии\\image_category.jpg");
        URL url = new URL(source);
        BufferedImage image = ImageIO.read(url);
        BufferedImage resizedImage = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, 250, 250, null);
        graphics2D.dispose();
        ImageIO.write(resizedImage, "jpg", file);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        file.delete();
        categoryDto.setImg(fileContent);
        categoryService.addCategory(categoryDto);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Полное обновление(изменение) категории")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id, @RequestParam String source) throws IOException {
        File file = new File("C:\\Users\\Чингиз\\Desktop\\учеба\\Web-технологии\\image_category.jpg");
        URL url = new URL(source);
        BufferedImage image = ImageIO.read(url);
        BufferedImage resizedImage = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, 250, 250, null);
        graphics2D.dispose();
        ImageIO.write(resizedImage, "jpg", file);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        file.delete();
        categoryDto.setImg(fileContent);
        try {
            return categoryService.updateCategory(categoryDto, id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление категории")
    public String deleteCategory(@PathVariable Long id) {
        try{
            return categoryService.deleteCategory(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @DeleteMapping("/deleteAll")
    @Operation(summary = "Удаление всех категорий")
    public void deleteAll() {
        categoryService.deleteAll();
    }
}
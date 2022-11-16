package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.errorhandlers.IdNotFoundException;
import web.tech.project.api.core.model.CategoryDto;
import web.tech.project.api.core.service.CategoryService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    @Operation(summary = "Получение списка всех категорий")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение категории по ID")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление новой категории")
    public void addCategory(@RequestBody CategoryDto categoryDto, @RequestParam String source) throws IOException {
        categoryService.addCategory(categoryDto, source);
    }

    @PutMapping("/{id}/update")
    @Operation(summary = "Полное обновление(изменение) категории")
    public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id, @RequestParam String source) throws IOException {
        try {
            return categoryService.updateCategory(categoryDto, id, source);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}/delete")
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
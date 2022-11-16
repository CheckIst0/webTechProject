package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.errorhandlers.IdNotFoundException;
import web.tech.project.api.core.model.MenuDto;
import web.tech.project.api.core.service.MenuService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

    @Operation(summary = "Проверка связи")
    @GetMapping("/test")
    public String test() {
        return "Hello!";
    }

    @Operation(summary = "Добавление новой позиции в меню")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public MenuDto addMenu(@RequestBody MenuDto menuDto, @RequestParam String source) throws IOException {
        return menuService.addMenu(menuDto, source);
    }

    @Operation(summary = "Получение позиции меню по ID записи в БД")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MenuDto getById(@PathVariable Long id) {
        try {
            return  menuService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Получение списка позиций меню с заданной категорией")
    @GetMapping(value = "/getByCategory/{categoryId}")
    public List<MenuDto> getMenuByCategoryId(@PathVariable int categoryId) {
        return menuService.getMenuByCategoryId(categoryId);
    }

    @PutMapping("/{id}/update")
    @Operation(summary = "Полное обновление(изменение) позиции меню с указанным ID")
    public MenuDto updateMenu(@RequestBody MenuDto menuDto, @PathVariable Long id, @RequestParam String source) throws IOException {
        try {
            return menuService.updateMenu(menuDto, id, source);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}/delete")
    @Operation(summary = "Удаление позиции меню с указанным ID")
    public String deleteMenu(@PathVariable Long id) {
        try {
            return menuService.deleteMenu(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @DeleteMapping("/deleteAll")
    @Operation(summary = "Удаление всех позиций в меню")
    public void deleteAll() {
        menuService.deleteAll();
    }
}

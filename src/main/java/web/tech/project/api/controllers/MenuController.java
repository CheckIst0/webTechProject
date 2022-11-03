package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.errorhandlers.IdNotFoundException;
import web.tech.project.api.core.model.MenuDto;
import web.tech.project.api.core.service.MenuService;

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
        File file = new File("C:\\Users\\Чингиз\\Desktop\\учеба\\Web-технологии\\image.jpg");
        URL url = new URL(source);
        BufferedImage image = ImageIO.read(url);
        BufferedImage resizedImage = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, 250, 250, null);
        graphics2D.dispose();
        ImageIO.write(resizedImage, "jpg", file);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        file.delete();
        menuDto.setImage(fileContent);
        return menuService.addMenu(menuDto);
    }

    @Operation(summary = "Получение позиции меню по ID записи в БД")
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public MenuDto getById(@PathVariable Long id) {
        try {
            return  menuService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Получение списка позиций меню с заданной категорией")
    @GetMapping(value = "/getMenuByCategory/{categoryId}")
    public List<MenuDto> getMenuByCategoryId(@PathVariable int categoryId) {
        return menuService.getMenuByCategoryId(categoryId);
    }

    @PatchMapping(value = "/setImageToMenu/{id}")
    @Operation(summary = "Обновление(изменение) картинки позиции меню с указанным ID")
    public void setImageToMenu(@RequestBody byte[] image, @PathVariable Long id) {
        try {
            menuService.setImageToMenu(image, id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Полное обновление(изменение) позиции меню с указанным ID")
    public MenuDto updateMenu(@RequestBody MenuDto menuDto, @PathVariable Long id, @RequestParam String source) throws IOException {
        File file = new File("C:\\Users\\Чингиз\\Desktop\\учеба\\Web-технологии\\image.jpg");
        URL url = new URL(source);
        BufferedImage image = ImageIO.read(url);
        BufferedImage resizedImage = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, 250, 250, null);
        graphics2D.dispose();
        ImageIO.write(resizedImage, "jpg", file);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        file.delete();
        menuDto.setImage(fileContent);
        try {
            return menuService.updateMenu(menuDto, id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @DeleteMapping("/delete/{id}")
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

    @DeleteMapping("/deleteAllById")
    @Operation(summary = "Удаление по списку id")
    public void deleteAllById(@RequestBody List<Long> ids) {
        menuService.deleteAllById(ids);
    }
}

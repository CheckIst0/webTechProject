package web.tech.project.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.model.MenuDto;
import web.tech.project.api.core.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;

//    @ApiOperation(value = "Проверка связи")
    @GetMapping("/test")
    public String test() {
        return "Hello!";
    }

//    @ApiOperation(value = "Добавление позиции в меню")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public MenuDto addMenu(@RequestBody MenuDto menuDto) {
        return menuService.addMenu(menuDto);
    }

//    @ApiOperation(value = "Получение позиции по ID записи в БД")
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public MenuDto getById(@PathVariable Long id) {
        return  menuService.getById(id);
    }

    @GetMapping(value = "/getMenuByCategory/{categoryId}")
    public List<MenuDto> getMenuByCategoryId(@PathVariable int categoryId) {
        return menuService.getMenuByCategoryId(categoryId);
    }
}

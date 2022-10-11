package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.MenuMapper;
import web.tech.project.api.core.model.MenuDto;
import web.tech.project.db.entity.Menu;
import web.tech.project.db.repository.CategoryRepository;
import web.tech.project.db.repository.MenuRepository;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MenuMapper menuMapper;

    public MenuDto addMenu(MenuDto menu) {
        if (categoryRepository.findAllCategoryId().contains(menu.getCategory().getId())) {
            Menu menuNew = menuMapper.map(menu, Menu.class);
            menuNew.setCategory(categoryRepository.getReferenceById(menu.getCategory().getId()));
            menuNew = menuRepository.save(menuNew);
            return menuMapper.map(menuNew, MenuDto.class);
        } else {
            Menu menuNew = menuMapper.map(menu, Menu.class);
            menuNew = menuRepository.save(menuNew);
            return menuMapper.map(menuNew, MenuDto.class);
        }
    }

    public MenuDto getById(Long id) {
        Menu menu = menuRepository.getReferenceById(id);
        return menuMapper.map(menu, MenuDto.class);
    }

    public List<MenuDto> getMenuByCategoryId(int categoryId) {
        List<Menu> menuList = menuRepository.findMenuByCategory(categoryId);
        return menuMapper.mapAsList(menuList, MenuDto.class);
    }
}

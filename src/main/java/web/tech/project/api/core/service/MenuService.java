package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.errorhandlers.CategoryNotFoundException;
import web.tech.project.api.core.mapper.CategoryMapper;
import web.tech.project.api.core.mapper.MenuMapper;
import web.tech.project.api.core.model.MenuDto;
import web.tech.project.db.entity.Category;
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
    @Autowired
    private CategoryMapper categoryMapper;

    public MenuDto addMenu(MenuDto menu) {
        Menu menuNew = menuMapper.map(menu, Menu.class);
        menuNew.setCategory(categoryRepository.getReferenceById(menu.getCategory().getId()));
        menuNew = menuRepository.save(menuNew);
        return menuMapper.map(menuNew, MenuDto.class);
    }

    public MenuDto getById(Long id) {
        Menu menu = menuRepository.getReferenceById(id);
        return menuMapper.map(menu, MenuDto.class);
    }

    public List<MenuDto> getMenuByCategoryId(int categoryId) {
        List<Menu> menuList = menuRepository.findMenuByCategory(categoryId);
        if (menuList.isEmpty()) {
            throw new CategoryNotFoundException(categoryId);
        }
        return menuMapper.mapAsList(menuList, MenuDto.class);
    }

    public void setImageToMenu(byte[] image, Long id) {
        menuRepository.setImageToMenu(image, id);
    }

    public MenuDto updateMenu(MenuDto menuDto, Long id) {
        Menu menu = menuRepository.getReferenceById(id);
        menu.setAvailable(menuDto.isAvailable());
        menu.setCategory(categoryMapper.map(menuDto.getCategory(), Category.class));
        menu.setDescription(menuDto.getDescription());
        menu.setMass(menuDto.getMass());
        menu.setImage(menuDto.getImage());
        menu.setPrice(menuDto.getPrice());
        menu.setTitle(menuDto.getTitle());
        menuRepository.save(menu);
        return menuMapper.map(menu, MenuDto.class);
    }

    public String deleteMenu(Long id) {
        menuRepository.deleteById(id);
        return "OK";
    }

    public void deleteAll() {
        menuRepository.deleteAll();
    }

    public void deleteAllById(List<Long> ids) {
        menuRepository.deleteAllByIdInBatch(ids);
    }
}

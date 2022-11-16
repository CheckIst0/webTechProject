package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.CategoryMapper;
import web.tech.project.api.core.model.CategoryDto;
import web.tech.project.db.entity.Category;
import web.tech.project.db.entity.Menu;
import web.tech.project.db.repository.CategoryRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        return categoryMapper.mapAsList(categoryRepository.findAll(), CategoryDto.class);
    }

    public CategoryDto getById(Long id) {
        Category result = categoryRepository.getReferenceById(id);
//        result.setImg(null);
//        List<Menu> menuList = result.getMenus();
//        for (int i = 0; i < menuList.size(); i++) {
//            menuList.get(i).setImage(null);
//        }
//        result.setMenus(menuList);
        return categoryMapper.map(result, CategoryDto.class);
    }

    public String setImageToCategory(byte[] image, Long id) {
        categoryRepository.setImageToCategory(image, id);
        return "OK";
    }

    public CategoryDto updateCategory(CategoryDto categoryDto, Long id, String source) throws IOException {
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
        Category category = categoryRepository.getReferenceById(id);
        category.setName(categoryDto.getName());
        category.setTitle(categoryDto.getTitle());
        category.setImg(categoryDto.getImg());
        categoryRepository.save(category);
        return categoryMapper.map(category, CategoryDto.class);
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "OK";
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    public void addCategory(CategoryDto categoryDto, String source) throws IOException {
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
        Category newCategory = categoryMapper.map(categoryDto, Category.class);
        categoryRepository.save(newCategory);
    }
}

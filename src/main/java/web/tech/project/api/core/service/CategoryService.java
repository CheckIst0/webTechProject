package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.CategoryMapper;
import web.tech.project.api.core.model.CategoryDto;
import web.tech.project.db.entity.Category;
import web.tech.project.db.repository.CategoryRepository;

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

    public String setImageToCategory(byte[] image, Long id) {
        categoryRepository.setImageToCategory(image, id);
        return "OK";
    }

    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
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

    public void addCategory(CategoryDto categoryDto) {
        Category newCategory = categoryMapper.map(categoryDto, Category.class);
        categoryRepository.save(newCategory);
    }
}

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
}

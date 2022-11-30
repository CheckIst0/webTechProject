package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.MealMapper;
import web.tech.project.api.core.model.MealDto;
import web.tech.project.db.entity.Meal;
import web.tech.project.db.repository.MealRepository;

import java.util.List;

@Service
public class MealService {
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MealMapper mealMapper;

    public List<MealDto> addMeals(List<MealDto> mealDtoList) {
        List<Meal> meals = mealMapper.mapAsList(mealDtoList, Meal.class);
        meals = mealRepository.saveAll(meals);
        return mealMapper.mapAsList(meals, MealDto.class);
    }

//    public MealDto getById(Long id) {
//        Meal meal = mealRepository.getReferenceById(id);
//        meal.setOrder(null);
//        return mealMapper.map(meal, MealDto.class);
//    }
//
//    public List<MealDto> getAll() {
//        List<Meal> meals = mealRepository.findAll();
//        for (Meal meal : meals) {
//            meal.setOrder(null);
//        }
//        return mealMapper.mapAsList(meals, MealDto.class);
//    }
}

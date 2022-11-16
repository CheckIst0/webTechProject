package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.model.MealDto;
import web.tech.project.api.core.service.MealService;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/meal")
public class MealController {
    @Autowired
    private MealService mealService;

    @Operation(summary = "Добавление заказанных клиентом блюд")
    @PostMapping("/addMeals")
    public List<MealDto> addMeals(@RequestBody List<MealDto> mealDtoList) {
        return mealService.addMeals(mealDtoList);
    }
}

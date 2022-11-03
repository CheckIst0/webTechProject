package web.tech.project.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.errorhandlers.IdNotFoundException;
import web.tech.project.api.core.model.TableDto;
import web.tech.project.api.core.service.TableService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @Operation(summary = "Получение списка всех столов")
    @GetMapping("/getAllTables")
    public List<TableDto> getAllTables() {
        return tableService.getAllTables();
    }

    @Operation(summary = "Изменение статуса стола с указанным ID на \"занято\"")
    @PutMapping("/takeATable/{id}")
    public void takeATable(@PathVariable Long id) {
        try {
            tableService.takeATable(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Изменение статуса стола с указанным ID на \"свободно\"")
    @PutMapping("/freeTable/{id}")
    public void freeTable(@PathVariable Long id){
        try {
            tableService.freeTable(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }

    @Operation(summary = "Добавление столика")
    @PostMapping("/add")
    public TableDto addTable(@RequestBody TableDto tableDto) {
        return tableService.addTable(tableDto);
    }

    @Operation(summary = "Удаление столика по ID")
    @DeleteMapping("/delete/{id}")
    public void deleteTable(Long id) {
        try {
            tableService.deleteTable(id);
        } catch (EntityNotFoundException e) {
            throw new IdNotFoundException(id);
        }
    }
}

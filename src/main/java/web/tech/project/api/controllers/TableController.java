package web.tech.project.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.tech.project.api.core.model.TableDto;
import web.tech.project.api.core.service.TableService;

import java.util.List;

@RestController
@RequestMapping("/api/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/getAllTables")
    public List<TableDto> getAllTables() {
        return tableService.getAllTables();
    }

    @PatchMapping("/takeATable/{id}")
    public void takeATable(@PathVariable Long id) {
        tableService.takeATable(id);
    }
}

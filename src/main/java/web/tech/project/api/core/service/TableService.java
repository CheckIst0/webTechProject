package web.tech.project.api.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.tech.project.api.core.mapper.TableMapper;
import web.tech.project.api.core.model.TableDto;
import web.tech.project.db.entity.Table;
import web.tech.project.db.repository.TableRepository;

import java.util.List;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private TableMapper tableMapper;

    public List<TableDto> getAllTables(){
        List<Table> tables = tableRepository.findAll();
        return tableMapper.mapAsList(tables, TableDto.class);
    }

    public void takeATable(Long id) {
        Table table = tableRepository.getReferenceById(id);
        table.setFree(true);
        tableRepository.save(table);
    }
}

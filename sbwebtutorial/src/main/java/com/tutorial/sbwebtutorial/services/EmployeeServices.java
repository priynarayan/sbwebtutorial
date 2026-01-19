package com.tutorial.sbwebtutorial.services;

import com.tutorial.sbwebtutorial.dto.EmployeeDTO;
import com.tutorial.sbwebtutorial.entities.EmployeeEntity;
import com.tutorial.sbwebtutorial.repositories.EmployeeRepository;
import jakarta.persistence.Id;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServices {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServices(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long employeeId) {
        //EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
        return employeeRepository
                .findById(employeeId)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO saveEmployeeData(EmployeeDTO inputEmployee){
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isExistsEmployeeById(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isExistsEmployeeById(employeeId);
        if(!exists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = isExistsEmployeeById(employeeId);
        if(!exists) return null;

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);

    }
}

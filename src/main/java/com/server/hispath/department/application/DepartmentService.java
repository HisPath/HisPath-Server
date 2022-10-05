package com.server.hispath.department.application;

import com.server.hispath.department.application.dto.DepartmentDto;
import com.server.hispath.department.domain.Department;
import com.server.hispath.department.domain.repository.DepartementRepository;
import com.server.hispath.exception.student.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartementRepository departmentRepository;

    @Transactional
    public Long create(DepartmentDto dto){
        Department savedDepartment = departmentRepository.save(Department.from(dto));
        return savedDepartment.getId();
    }

    @Transactional
    public DepartmentDto find(Long id) {
        Department department = this.findById(id);
        return DepartmentDto.from(department);
    }

    @Transactional
    public List<DepartmentDto> findAll() {
        List<Department> department = departmentRepository.findAll();
        return department.stream()
                .map(DepartmentDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public DepartmentDto update(Long id, DepartmentDto dto) {
        Department department = departmentRepository.findById(id).orElseThrow(StudentNotFoundException::new);;
        department.update(dto);
        return DepartmentDto.from(department);
    }

    @Transactional
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }
}

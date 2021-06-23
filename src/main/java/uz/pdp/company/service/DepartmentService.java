package uz.pdp.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.company.entity.Company;
import uz.pdp.company.entity.Department;
import uz.pdp.company.payload.DepartmentDto;
import uz.pdp.company.repository.CompanyRepository;
import uz.pdp.company.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;


    public ResponseEntity<?> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return ResponseEntity.ok(departmentList);
    }

    public ResponseEntity<?> getById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (!departmentOptional.isPresent()){
            return ResponseEntity.status(404).body("Bunday bulim topilmadi");
        }
        Department department = departmentOptional.get();
        return ResponseEntity.ok(department);
    }

    public ResponseEntity<?> add(DepartmentDto departmentDto) {
        boolean exists = departmentRepository.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId());
        if (exists){
            return ResponseEntity.status(409).body("Bunday bulim mavjud");
        }
        Department department=new Department();
        department.setName(departmentDto.getName());
        Optional<Company> companyOptional = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyOptional.isPresent()){
            return ResponseEntity.status(404).body("Bunday kompanya mavjud emas");
        }
        Company company = companyOptional.get();
        department.setCompany(company);
        departmentRepository.save(department);
        return ResponseEntity.ok("Saqlandi");
    }

    public ResponseEntity<?> edit(DepartmentDto departmentDto, Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (!departmentOptional.isPresent()){
            return ResponseEntity.status(404).body("Bunday bulim mavjud emas");
        }
        Optional<Company> companyOptional = companyRepository.findById(departmentDto.getCompanyId());
        if (!companyOptional.isPresent()){
            return ResponseEntity.status(404).body("Bunday companya mavjud emas");
        }
        boolean exists = departmentRepository.existsByName(departmentDto.getName());
        if (exists){
            return ResponseEntity.status(409).body("Bunday nomli bulim bu kompanyada mavjud");
        }
        Department department = departmentOptional.get();
        department.setName(departmentDto.getName());
        department.setCompany(companyOptional.get());
        departmentRepository.save(department);
        return ResponseEntity.ok("Tahrirlandi");
    }
}

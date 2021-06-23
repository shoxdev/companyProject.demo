package uz.pdp.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.company.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    boolean existsByNameAndCompanyId(String name, Long company_id);

    boolean existsByName(String name);
}

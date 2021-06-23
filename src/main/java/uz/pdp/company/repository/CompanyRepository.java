package uz.pdp.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.company.entity.Address;
import uz.pdp.company.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    boolean existsByAddressIdAndCompanyName(Long address_id, String companyName);
}

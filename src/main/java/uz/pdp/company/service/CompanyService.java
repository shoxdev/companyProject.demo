package uz.pdp.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.company.entity.Address;
import uz.pdp.company.entity.Company;
import uz.pdp.company.payload.CompanyDto;
import uz.pdp.company.repository.AddressRepository;
import uz.pdp.company.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<?> getAll() {
        List<Company> companyList = companyRepository.findAll();
        return ResponseEntity.ok(companyList);
    }

    public ResponseEntity<?> getById(Long id) {
        Optional<Company> optional = companyRepository.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(409).body("Bunday Comany topilmadi");
        }
        return ResponseEntity.status(201).body(optional.get());
    }

    public ResponseEntity<?> add(CompanyDto companyDto) {
        Optional<Address> addressOptional = addressRepository.findById(companyDto.getAddressId());
        if (!addressOptional.isPresent()){
            return ResponseEntity.status(409).body("Bunday address mavjud emas");
        }
        Address address = addressOptional.get();
        boolean exists = companyRepository.existsByAddressIdAndCompanyName(companyDto.getAddressId(), companyDto.getCompanyName());
        if (exists){
            return ResponseEntity.status(409).body("Bu address da bunday companiya mavjud");
        }
        Company company=new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setAddress(address);
        company.setDirectName(companyDto.getDirectName());
        companyRepository.save(company);
        return ResponseEntity.status(201).body("Saqlandi");
    }

    public ResponseEntity<?> edit(Long id,CompanyDto companyDto) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()){
            return ResponseEntity.status(409).body("Bunday Companya mavjud emas");
        }
        Company company = companyOptional.get();
        company.setCompanyName(companyDto.getCompanyName());
        company.setDirectName(companyDto.getDirectName());
        Optional<Address> addressOptional = addressRepository.findById(companyDto.getAddressId());
        if (!addressOptional.isPresent()) {
            return ResponseEntity.status(409).body("Bunday Address mavjud emas");
        }
        Address address = addressOptional.get();
        company.setAddress(address);
        companyRepository.save(company);
        return ResponseEntity.status(202).body("Tahrirlandi");
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            companyRepository.deleteById(id);
            return ResponseEntity.status(201).body("O'chirildi");
        }catch (Exception e){
            return ResponseEntity.status(409).body("Xatolik");
        }
    }
}

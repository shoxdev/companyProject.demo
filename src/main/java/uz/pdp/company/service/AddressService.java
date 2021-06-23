package uz.pdp.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.company.entity.Address;
import uz.pdp.company.payload.AddressDto;
import uz.pdp.company.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public ResponseEntity<?> getAll() {
        List<Address> addressList = addressRepository.findAll();
        return ResponseEntity.ok(addressList);
    }

    public ResponseEntity<?> getById(Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.ok("Bunday address mavjud emas");
        }
        return ResponseEntity.ok(optional.get());

    }

    public ResponseEntity<?> add(AddressDto addressDto) {
        Address address=new Address();
        boolean exists = addressRepository.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber());
        if (exists){
            return ResponseEntity.status(409).body("Bunday addres mavjud");
        }
        address.setHomeNumber(addressDto.getHomeNumber());
        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return ResponseEntity.ok("Saqlandi");

    }

    public ResponseEntity<?> edit(AddressDto addressDto, Long id) {
        Optional<Address> optional = addressRepository.findById(id);
        if (!optional.isPresent()){
            return ResponseEntity.status(409).body("Bunday address mavjud emas");
        }
        Address address = optional.get();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepository.save(address);
        return ResponseEntity.ok("Tahrirlandi");
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            addressRepository.deleteById(id);
            return ResponseEntity.ok("ochirildi");
        }catch (Exception e){
            return ResponseEntity.ok("Xatolik");
        }
    }
}

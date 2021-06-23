package uz.pdp.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.company.payload.AddressDto;
import uz.pdp.company.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;


    /**
     * Hamma addresslarni olish
     * @return responseEntity orqali javob qaytayapti
     */
    @GetMapping
    public ResponseEntity<?> getAll(){
        return addressService.getAll();
    }


    /**
     *Id orqali address ni olish
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return addressService.getById(id);
    }


    /**
     * Yangi addres saqlandi
     * @param addressDto dan db ga jsonorqali malumot kiritildi
     * @return
     */
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody AddressDto addressDto){
        return addressService.add(addressDto);
    }


    @PutMapping("/{id}")
    public  ResponseEntity<?> edit(@Valid  @RequestBody AddressDto addressDto, @PathVariable Long id){
        return addressService.edit(addressDto,id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return addressService.delete(id);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

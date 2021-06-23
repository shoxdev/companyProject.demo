package uz.pdp.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.company.payload.CompanyDto;
import uz.pdp.company.service.CompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return companyService.getAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CompanyDto companyDto){
        return companyService.add(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Long id,@RequestBody CompanyDto companyDto){
        return companyService.edit(id,companyDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return companyService.delete(id);
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

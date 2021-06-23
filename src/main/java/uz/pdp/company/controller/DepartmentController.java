package uz.pdp.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.company.payload.DepartmentDto;
import uz.pdp.company.service.DepartmentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return departmentService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody DepartmentDto departmentDto){
        return departmentService.add(departmentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid  @RequestBody DepartmentDto departmentDto, @PathVariable Long id){
        return departmentService.edit(departmentDto,id);
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

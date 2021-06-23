package uz.pdp.company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.company.entity.Company;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotNull(message = "Bo'lim bush bulmasin")
    private String name;

    @NotNull(message = "Companya bush bo'lmasin")
    private Long companyId;
}

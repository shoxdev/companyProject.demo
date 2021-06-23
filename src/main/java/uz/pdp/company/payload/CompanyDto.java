package uz.pdp.company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.company.entity.Address;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @NotNull(message = "Companyani nomi bush.")
    private String companyName;

    @NotNull(message = "Derictor ismi bush")
    private String directName;

    @NotNull(message = "Address bush")
    private Long addressId;
}

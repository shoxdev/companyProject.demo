package uz.pdp.company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;

    @NotNull(message = "Street bush bulmasin")
    private String street;

    @NotNull(message = "HomeNumber bush bulmasin")
    private String homeNumber;
}

package uz.pdp.company.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private Long addressId;
    private Long departmentId;
}

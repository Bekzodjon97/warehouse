package uz.pdp.appwarehouse.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputProductDto {

    private Integer productId;

    private Double  amount;

    private Double  price;

    private Date expireDate;

    private Integer outputId;

}

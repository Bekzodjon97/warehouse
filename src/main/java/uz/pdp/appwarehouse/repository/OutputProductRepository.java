package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.OutputProduct;
import uz.pdp.appwarehouse.payload.DailyIncome;

import java.sql.Timestamp;
import java.util.List;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
    boolean existsByProductIdAndAmountAndPriceAndOutputsId(Integer product_id, Double amount, Double price, Integer outputs_id);

    @Query(value = "select date from outputs ", nativeQuery = true)
    List<Timestamp> getallTimestamp();



    @Query(value = "SELECT\n" +
            "              p.name, \n" +
            "              op.amount, \n" +
            "              op.price\n" +
            "FROM\n" +
            "              output_product op\n" +
            "              INNER JOIN product p ON p.id=op.product_id\n" +
            "              INNER JOIN outputs o ON o.id=op.output_id\n" +
            "WHERE i.date=:date;", nativeQuery = true)
    DailyIncome getMaxOutputProduct(Timestamp date);

}

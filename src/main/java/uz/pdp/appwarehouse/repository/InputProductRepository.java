package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.InputProduct;
import uz.pdp.appwarehouse.payload.DailyIncome;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

    @Query(value = "select date from inputs", nativeQuery = true)
    List<Timestamp> getAllTimeStamp();


    @Query(value = "SELECT\n" +
            "              p.name, \n" +
            "              ip.amount, \n" +
            "              ip.price\n" +
            "FROM\n" +
            "              input_product ip\n" +
            "              INNER JOIN product p ON p.id=ip.product_id\n" +
            "              INNER JOIN inputs i ON i.id=ip.input_id\n" +
            "WHERE i.date=:date;", nativeQuery = true)
    DailyIncome getDailyIncome(Timestamp date);

    @Query(value = "select expire_date from input_product", nativeQuery = true)
    List<Date> getAllDate();

    @Query(value = "select p.name, ip.amount, ip.price from input_product ip join product p on p.id=ip.product_id " +
            "where ip.expire_dat=:date",nativeQuery = true)
    DailyIncome getExpireDateProduct(Date date);


}

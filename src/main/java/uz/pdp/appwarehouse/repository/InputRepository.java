package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appwarehouse.entity.Input;

import java.util.List;

public interface InputRepository extends JpaRepository<Input, Integer> {

    @Query(value = "select code from Input", nativeQuery = true)
    List<Integer> getCodeNative();

}

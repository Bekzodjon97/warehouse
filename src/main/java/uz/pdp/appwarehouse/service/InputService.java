package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addInputService(InputDto inputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bunday ombor mavjud emas",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Bunday yetkazuvchi mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Inputs inputs =new Inputs();
        inputs.setFactureNumber(inputDto.getFactureNumber());
        inputs.setCode(getCode());
        inputs.setCurrency(optionalCurrency.get());
        inputs.setSupplier(optionalSupplier.get());
        inputs.setWarehouse(optionalWarehouse.get());
        inputs.setDate(Timestamp.valueOf(LocalDateTime.now()));
        inputRepository.save(inputs);
        return new Result("Muvaffaqiyatli saqlandi", true);

    }
    public List<Inputs> getInput(){
        return inputRepository.findAll();
    }
    public Inputs getInputById(Integer id){
        Optional<Inputs> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Inputs();
        }
        return optionalInput.get();
    }
    public  Result deleteInput(Integer id){
        Optional<Inputs> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Result("Input not found", false);
        }
        try {
            inputRepository.deleteById(id);
            return new Result("Input deleted", true);
        } catch (Exception e) {
            return new Result("Input not deleted", false);
        }
    }
    public Result updateInput(Integer id, InputDto inputDto){
        Optional<Inputs> optionalInput = inputRepository.findById(id);
        if (!optionalInput.isPresent()) {
            return new Result("Bunday kirim mavjud emas", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bunday ombor mavjud emas",false);
        }
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (!optionalSupplier.isPresent()) {
            return new Result("Bunday yetkazuvchi mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Inputs inputs =optionalInput.get();
        inputs.setFactureNumber(inputDto.getFactureNumber());
        inputs.setCurrency(optionalCurrency.get());
        inputs.setSupplier(optionalSupplier.get());
        inputs.setWarehouse(optionalWarehouse.get());
        inputs.setDate(Timestamp.valueOf(LocalDateTime.now()));
        inputRepository.save(inputs);
        return new Result("Muvaffaqiyatli o'zgartirildi", true);
    }
    public Integer getCode(){
        List<Integer> integerList = inputRepository.getCodeNative();
        Integer maxCode=0;
        for (Integer integer : integerList) {
            if (integer>maxCode){
                maxCode=integer;
            }
        }
        return maxCode;
    }
}


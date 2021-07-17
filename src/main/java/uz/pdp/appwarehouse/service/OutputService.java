package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.*;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addOutputService(OutputDto outputDto){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bunday ombor mavjud emas",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) {
            return new Result("Bunday xaridor mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Output output=new Output();
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCode(getCode());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setDate(Timestamp.valueOf(LocalDateTime.now()));
        outputRepository.save(output);
        return new Result("Muvaffaqiyatli saqlandi", true);

    }
    public List<Output> getOutput(){
        return outputRepository.findAll();
    }
    public  Output getOutputById(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Output();
        }
        return optionalOutput.get();
    }
    public  Result deleteOutput(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Result("Output not found", false);
        }
        try {
            outputRepository.deleteById(id);
            return new Result("Output deleted", true);
        } catch (Exception e) {
            return new Result("Output not deleted", false);
        }
    }
    public Result updateOutput(Integer id, OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (!optionalOutput.isPresent()) {
            return new Result("Chiqim mavjud emas", false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (!optionalWarehouse.isPresent()) {
            return new Result("Bunday ombor mavjud emas ",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (!optionalClient.isPresent()) {
            return new Result("Bunday xaridor mavjud emas",false);
        }
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (!optionalCurrency.isPresent()) {
            return new Result("Bunday valyuta mavjud emas",false);
        }
        Output output=optionalOutput.get();
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setDate(Timestamp.valueOf(LocalDateTime.now()));
        outputRepository.save(output);
        return new Result("Muvaffaqiyatli o'zgartirildi", true);
    }
    public Integer getCode(){
        List<Integer> integerList = outputRepository.getCode();
        Integer maxCode=0;
        for (Integer integer : integerList) {
            if (integer>maxCode){
                maxCode=integer;
            }
        }
        return maxCode;
    }
}


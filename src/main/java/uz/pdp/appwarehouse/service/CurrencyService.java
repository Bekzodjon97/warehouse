package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrencyService(Currency currency){
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName) {
            return new Result("Bunday pul birligi mavjud", false);
        }
        currencyRepository.save(currency);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<Currency> getCurrency(){
        return currencyRepository.findAll();
    }
    public  Currency getCurrencyById(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Currency();
        }
        return optionalCurrency.get();
    }
    public  Result deleteCurrency(Integer id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Result("Currency not found", false);
        }
        currencyRepository.deleteById(id);
        return new Result("Currency deleted", true);
    }
    public Result updateCurrency(Integer id, Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return  new Result("Currency not found", false);
        }
        Currency editedCurrency = optionalCurrency.get();
        editedCurrency.setName(currency.getName());
        currencyRepository.save(editedCurrency);
        return new Result("Currency edited", true);
    }
}


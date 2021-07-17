package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody Currency currency){
        return currencyService.addCurrencyService(currency);

    }

    //READ
    @GetMapping
    public List<Currency> get(){
        return currencyService.getCurrency();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Currency getById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  currencyService.deleteCurrency(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Currency currency){
        return currencyService.updateCurrency(id, currency);
    }
}

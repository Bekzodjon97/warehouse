package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Inputs;
import uz.pdp.appwarehouse.payload.InputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping(value = "/input")
public class InputController {

    @Autowired
    InputService inputService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody InputDto inputDto){
        return inputService.addInputService(inputDto);

    }

    //READ
    @GetMapping
    public List<Inputs> get(){
        return inputService.getInput();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Inputs getById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  inputService.deleteInput(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.updateInput(id, inputDto);
    }
}

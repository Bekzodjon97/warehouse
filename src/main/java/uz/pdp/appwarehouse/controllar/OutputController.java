package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Outputs;
import uz.pdp.appwarehouse.payload.OutputDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.OutputService;

import java.util.List;

@RestController
@RequestMapping(value = "/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody OutputDto outputDto){
        return outputService.addOutputService(outputDto);

    }

    //READ
    @GetMapping
    public List<Outputs> get(){
        return outputService.getOutput();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Outputs getById(@PathVariable Integer id){
        return outputService.getOutputById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  outputService.deleteOutput(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        return outputService.updateOutput(id, outputDto);
    }
}

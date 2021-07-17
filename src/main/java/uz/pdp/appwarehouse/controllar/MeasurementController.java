package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    //READ
    @GetMapping
    public List<Measurement> get(){
        return measurementService.getMeasurement();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Measurement getById(@PathVariable Integer id){
        return measurementService.getMeasurementById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  measurementService.deleteMeasurement(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.updateMeasurement(id, measurement);
    }
}

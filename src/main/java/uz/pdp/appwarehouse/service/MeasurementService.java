package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) {
            return new Result("Bunday o'lov birligi mavjud", false);
        }
        measurementRepository.save(measurement);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<Measurement> getMeasurement(){
        return measurementRepository.findAll();
    }
    public  Measurement getMeasurementById(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Measurement();
        }
        return optionalMeasurement.get();
    }
    public  Result deleteMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Result("Measurement not found", false);
        }
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted", true);
    }
    public Result updateMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return  new Result("Measurement not found", false);
        }
        Measurement editedMeasurement = optionalMeasurement.get();
        editedMeasurement.setName(measurement.getName());
        measurementRepository.save(editedMeasurement);
        return new Result("Measurement edited", true);
    }
}


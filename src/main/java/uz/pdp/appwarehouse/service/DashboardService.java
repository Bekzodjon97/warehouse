package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.payload.DailyIncome;
import uz.pdp.appwarehouse.repository.InputProductRepository;
import uz.pdp.appwarehouse.repository.OutputProductRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    OutputProductRepository outputProductRepository;


    public List<DailyIncome> dailyIncome(){
        List<Timestamp> allTimeStamp = inputProductRepository.getAllTimeStamp();
        List<DailyIncome> dailyIncomes=new ArrayList<>();
        for (Timestamp timestamp : allTimeStamp) {
            if (timestamp.getDay()==Timestamp.valueOf(LocalDateTime.now()).getDay()){
                DailyIncome dailyIncome = inputProductRepository.getDailyIncome(timestamp);
                dailyIncomes.add(dailyIncome);
            }
        }
        return dailyIncomes;
    }

    public DailyIncome maxDailyOutputProduct(){
        List<Timestamp> allTimeStamp = outputProductRepository.getallTimestamp();
        List<DailyIncome> dailyIncomes=new ArrayList<>();
        for (Timestamp timestamp : allTimeStamp) {
            if (timestamp.getDay()==Timestamp.valueOf(LocalDateTime.now()).getDay()){
                DailyIncome dailyIncome = outputProductRepository.getMaxOutputProduct(timestamp);
                dailyIncomes.add(dailyIncome);
            }
        }
        double maxAmount=0;
        for (DailyIncome dailyIncome : dailyIncomes) {
            if (dailyIncome.getAmount()>maxAmount){
                maxAmount=dailyIncome.getAmount();
            }
        }
        DailyIncome selectedDailyIncome = null;
        for (DailyIncome dailyIncome : dailyIncomes) {
            if (dailyIncome.getAmount()==maxAmount){
                selectedDailyIncome=dailyIncome;
            }
        }
        return selectedDailyIncome;
    }

    public Integer countExpireDateProduct(){
        List<Date> dateList = inputProductRepository.getAllDate();
        List<DailyIncome> dailyIncomes=new ArrayList<>();
        for (Date date : dateList) {
            if (date.getDay()>LocalDate.now().getDayOfMonth()-10){
                DailyIncome dailyIncome = inputProductRepository.getExpireDateProduct(date);
                dailyIncomes.add(dailyIncome);
            }
        }
        return dailyIncomes.size();
    }
}

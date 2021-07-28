package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouse.payload.DailyIncome;
import uz.pdp.appwarehouse.service.DashboardService;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/dailyIncome")
    public List<DailyIncome> dailyIncome(){
        return dashboardService.dailyIncome();
    }

    @GetMapping("/maxOutputProduct")
    public DailyIncome getMaxOutputProduct(){
        return dashboardService.maxDailyOutputProduct();
    }

    @GetMapping("expiredProduct")
    public Integer countExpiredPruduct(){
        return dashboardService.countExpireDateProduct();
    }
}

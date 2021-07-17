package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.ClientService;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    //CREATE
    @PostMapping
    public Result add(@RequestBody Client client){
        return clientService.addClientService(client);

    }

    //READ
    @GetMapping
    public List<Client> get(){
        return clientService.getClient();
    }


    //READ BY ID
    @GetMapping(value = "/{id}")
    public Client getById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id){
        return  clientService.deleteClient(id);
    }

    //UPDATE
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @RequestBody Client client){
        return clientService.updateClient(id, client);
    }
}

package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Client;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClientService(Client client){
        boolean existsByName = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByName) {
            return new Result("Bunday raqamli haridor mavjud", false);
        }
        clientRepository.save(client);
        return new Result("Muaffaqiyatli saqlandi",true);
    }
    public List<Client> getClient(){
        return clientRepository.findAll();
    }
    public  Client getClientById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Client();
        }
        return optionalClient.get();
    }
    public  Result deleteClient(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return new Result("Client not found", false);
        }
        clientRepository.deleteById(id);
        return new Result("Client deleted", true);
    }
    public Result updateClient(Integer id, Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) {
            return  new Result("Client not found", false);
        }
        Client editedClient = optionalClient.get();
        editedClient.setName(client.getName());
        clientRepository.save(editedClient);
        return new Result("Client edited", true);
    }
}


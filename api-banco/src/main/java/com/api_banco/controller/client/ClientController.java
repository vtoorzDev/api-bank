package com.api_banco.controller.client;

import com.api_banco.dto.requestDTO.client.ClientRequestDTO;
import com.api_banco.dto.responseDTO.client.ClientResponseDTO;
import com.api_banco.service.client.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ClientResponseDTO registeClient(@RequestBody ClientRequestDTO clientRequestDTO){
        return clientService.registerClient(clientRequestDTO);
    }
    @GetMapping("/listAll")
    public List<ClientResponseDTO> listAllClients(){
        return clientService.findAllClients();
    }
    @GetMapping("/listByCpf/{cpf}")
    public ClientResponseDTO findByCpf(@PathVariable String cpf){
        return clientService.FindByCpf(cpf);
    }

    @PutMapping("/update/{cpf}")
    public ClientResponseDTO updateClient(@PathVariable String cpf, @RequestBody ClientRequestDTO clientRequestDTO){
        return clientService.updateClients(clientRequestDTO );
    }

    @DeleteMapping("/delete/{cpf}")
    public void deleteClient(@PathVariable String cpf){
        clientService.deleteClient(cpf);
    }
}

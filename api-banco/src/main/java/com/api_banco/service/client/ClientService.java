package com.api_banco.service.client;

import com.api_banco.dto.requestDTO.client.ClientRequestDTO;
import com.api_banco.dto.responseDTO.client.ClientResponseDTO;
import com.api_banco.entity.client.ClientEntity;
import com.api_banco.exception.client.ClientException;
import com.api_banco.repository.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    private ClientResponseDTO transformResponse(ClientEntity clientEntity){
        ClientResponseDTO clienteResponseDTO = new ClientResponseDTO();

        clienteResponseDTO.setId(clientEntity.getId());
        clienteResponseDTO.setName(clientEntity.getName());
        clienteResponseDTO.setCpf(clientEntity.getCpf());
        clienteResponseDTO.setPhone(clientEntity.getPhone());
        clienteResponseDTO.setWage(clientEntity.getWage());

        return clienteResponseDTO;
    }

    public ClientResponseDTO registerClient(ClientRequestDTO clienteRequestDTO){
        if (clientRepository.existsByCpf(clienteRequestDTO.getCpf())) {
            throw new ClientException("Erro ao registrar cliente");
        }
        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setName(clienteRequestDTO.getName());
        clientEntity.setCpf(clienteRequestDTO.getCpf());
        clientEntity.setPhone(clienteRequestDTO.getPhone());
        clientEntity.setWage(clienteRequestDTO.getWage());

        clientRepository.save(clientEntity);

        return transformResponse(clientEntity);
    }

    public List<ClientResponseDTO> findAllClients(){
         List<ClientEntity> clients = clientRepository.findAll();
         List<ClientResponseDTO> clientsResponse = new ArrayList<>();

         for (ClientEntity client : clients){
             clientsResponse.add(transformResponse(client));
         }
         return clientsResponse;
    }

    public ClientResponseDTO FindByCpf(String cpf){
        Optional<ClientEntity> clientFound = clientRepository.findByCpf(cpf);
        return clientFound.map(this::transformResponse).orElse(null);
    }

    public ClientResponseDTO updateClients(ClientRequestDTO clientRequestDTO) {
        if (clientRepository.existsByCpf(clientRequestDTO.getCpf())){
            Optional<ClientEntity> clientEntity = clientRepository.findByCpf(clientRequestDTO.getCpf());

            ClientEntity client = clientEntity.get();

            client.setName(clientRequestDTO.getName());
            client.setPhone(clientRequestDTO.getPhone());
            client.setWage(clientRequestDTO.getWage());

            clientRepository.save(client);

            return transformResponse(client);
        }
        throw new ClientException("Erro ao editar cliente");
    }

    public void deleteClient(String cpf){
        if (clientRepository.existsByCpf(cpf)){
            clientRepository.deleteByCpf(cpf);
        }
    }
}

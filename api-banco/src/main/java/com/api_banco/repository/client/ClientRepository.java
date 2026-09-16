package com.api_banco.repository.client;

import com.api_banco.entity.client.ClientEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    boolean existsByCpf(String cpf);
    Optional<ClientEntity> findByCpf(String cpf);
    void delete(String cpf);
}

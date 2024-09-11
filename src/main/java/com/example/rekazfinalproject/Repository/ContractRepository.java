package com.example.rekazfinalproject.Repository;

import com.example.rekazfinalproject.Model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    Contract findContractById(Integer id);
}

package com.example.rekazfinalproject.Service;


import com.example.rekazfinalproject.Api.ApiException;
import com.example.rekazfinalproject.DTO.ContractDTO;
import com.example.rekazfinalproject.Model.Contract;
import com.example.rekazfinalproject.Model.Investor;
import com.example.rekazfinalproject.Model.Owner;
import com.example.rekazfinalproject.Model.Project;
import com.example.rekazfinalproject.Repository.ContractRepository;
import com.example.rekazfinalproject.Repository.InvestorRepository;
import com.example.rekazfinalproject.Repository.OwnerRepository;
import com.example.rekazfinalproject.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor


public class ContractService {

    private final ContractRepository contractRepository;
    private final InvestorRepository investorRepository;
    private final OwnerRepository ownerRepository;
    private final ProjectRepository projectRepository;

    public List<Contract> getAllContract(){
        return contractRepository.findAll();
    }

    public void addContract(int investorId , int ownerId , int projectId , ContractDTO contractDTO){
        Investor investor = investorRepository.findInvestorById(investorId);
        if(investor==null){
            throw new ApiException("investor not found");
        }

        Owner owner = ownerRepository.findOwnerById(ownerId);
        if(owner==null){
            throw new ApiException("owner not found");
        }

        Project project = projectRepository.findProjectById(projectId);
        if(project==null){
            throw new ApiException("project not found");
        }



        Contract contract = new Contract(null,contractDTO.getTerms(), LocalDate.now(),contractDTO.getEndDate(),contractDTO.getStatus(),investor,owner,project);
        contractRepository.save(contract);

    }


    public void updateContract( int id , Contract contract){
        Contract contract1 = contractRepository.findContractById(id);
        if(contract1==null){
            throw new ApiException("contract not found");
        }
        contract1.setEndDate(contract.getEndDate());
        // contract1.setStartDate(contract.getStartDate());
        contract1.setStatus(contract.getStatus());
        contract1.setTerms(contract.getTerms());
        contractRepository.save(contract1);

    }

    public void deleteContract(int id){
        Contract contract1 = contractRepository.findContractById(id);
        if(contract1==null){
            throw new ApiException("contract not found");
        }
        contractRepository.delete(contract1);
    }


}

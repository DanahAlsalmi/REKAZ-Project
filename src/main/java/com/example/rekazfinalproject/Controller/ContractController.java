package com.example.rekazfinalproject.Controller;

import com.example.rekazfinalproject.DTO.ContractDTO;
import com.example.rekazfinalproject.Model.Contract;
import com.example.rekazfinalproject.Service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contract")


public class ContractController {

    private final ContractService contractService;

    @GetMapping("/get-all-contracts")
    public ResponseEntity getAllContracts(){
        return ResponseEntity.status(200).body(contractService.getAllContract());
    }

    @PostMapping("/add-contract/{investorid}/{ownerid}/{projectid}")

    public ResponseEntity addContract(@PathVariable int investorid , @PathVariable int ownerid , @PathVariable int projectid , @Valid @RequestBody ContractDTO contractDTO){
        contractService.addContract(investorid,ownerid,projectid,contractDTO);
        return ResponseEntity.status(200).body("contract added successfully");
    }

    @PutMapping("/update-contract/{id}")
    public ResponseEntity updateContract(@PathVariable int id, @Valid  @RequestBody Contract contract){
        contractService.updateContract(id, contract);
        return ResponseEntity.status(200).body("contract updated successfully");
    }

//    @DeleteMapping("/delete-contract/{id}")
//    public ResponseEntity deleteContract(@PathVariable int id){
//        contractService.deleteContract(id);
//        return ResponseEntity.status(200).body("contract deleted successfully");
//    }
}

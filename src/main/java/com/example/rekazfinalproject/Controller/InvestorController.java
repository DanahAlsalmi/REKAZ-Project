package com.example.rekazfinalproject.Controller;


import com.example.rekazfinalproject.DTO.InvestorDTO;
import com.example.rekazfinalproject.Service.InvestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/investor")
@RequiredArgsConstructor
public class InvestorController {

    //*** All CRUD Done by Danah ****
    private final InvestorService investorService;

    @GetMapping("/get")
    public ResponseEntity getAllInvestor(){
        return ResponseEntity.status(200).body(investorService.getAllInvestor());
    }

    @PostMapping("/register")
    public ResponseEntity registerInvestor(@Valid @RequestBody InvestorDTO investorDTO) {
        investorService.registerInvestor(investorDTO);
        return ResponseEntity.status(200).body("Investor registered successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInvestor(@PathVariable Integer id, @Valid @RequestBody InvestorDTO investorDTO) {
        investorService.updateInvestor(id, investorDTO);
        return ResponseEntity.status(200).body("Investor updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvestor(@PathVariable Integer id) {
        investorService.deleteInvestor(id);
        return ResponseEntity.status(200).body("Investor deleted successfully");
    }
}

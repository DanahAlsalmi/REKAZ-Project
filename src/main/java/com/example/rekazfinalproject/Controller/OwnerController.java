package com.example.rekazfinalproject.Controller;

import com.example.rekazfinalproject.DTO.OwnerDTO;
import com.example.rekazfinalproject.Service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    //*** All CRUD Done by Danah ****
    @GetMapping("/get")
    public ResponseEntity getAllOwners(){
        return ResponseEntity.status(200).body(ownerService.getAllOwners());
    }

    @PostMapping("/register")
    public ResponseEntity registerOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        ownerService.registerOwner(ownerDTO);
        return ResponseEntity.status(200).body("Owner registered successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOwner(@PathVariable Integer id, @Valid @RequestBody OwnerDTO ownerDTO) {
        ownerService.updateOwner(id, ownerDTO);
        return ResponseEntity.status(200).body("Owner updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOwner(@PathVariable Integer id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.status(200).body("Owner deleted successfully");
    }
}

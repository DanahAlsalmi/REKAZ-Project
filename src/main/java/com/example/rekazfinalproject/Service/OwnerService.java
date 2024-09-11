package com.example.rekazfinalproject.Service;

import com.example.rekazfinalproject.Api.ApiException;
import com.example.rekazfinalproject.DTO.OwnerDTO;
import com.example.rekazfinalproject.Model.Owner;
import com.example.rekazfinalproject.Model.User;
import com.example.rekazfinalproject.Repository.OwnerRepository;
import com.example.rekazfinalproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {


    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    //*** All CRUD Done by Danah ****
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public void registerOwner(OwnerDTO ownerDTO) {
        // Create and populate User entity
        User user = new User();
        user.setUsername(ownerDTO.getUsername());
        user.setPassword(ownerDTO.getPassword());
        user.setEmail(ownerDTO.getEmail());
        user.setRole("OWNER");

        // Create and populate Owner entity
        Owner owner = new Owner();
        owner.setCommercialRegister(ownerDTO.getCommercialRegister());
        owner.setScopeOfWork(ownerDTO.getScopeOfWork());
        owner.setNumOfProject(ownerDTO.getNumOfProject());
        owner.setCreatedAt(LocalDate.now());
        owner.setUser(user);

        user.setOwner(owner);

        // Save User and Owner
        userRepository.save(user);
    }

    public void updateOwner(Integer id, OwnerDTO ownerDTO) {
        Owner owner = ownerRepository.findOwnerById(id);
        if (owner == null) {
            throw new ApiException("Owner not found");
        }

        owner.setCommercialRegister(ownerDTO.getCommercialRegister());
        owner.setScopeOfWork(ownerDTO.getScopeOfWork());
        owner.setNumOfProject(ownerDTO.getNumOfProject());
        ownerRepository.save(owner);

        User user = owner.getUser();
        user.setUsername(ownerDTO.getUsername());
        user.setPassword(ownerDTO.getPassword());
        user.setEmail(ownerDTO.getEmail());
        userRepository.save(user);
    }

    public void deleteOwner(Integer id) {
        User owner = userRepository.findUserById(id);
        if (owner == null) {
            throw new ApiException("Owner not found");
        }
        userRepository.delete(owner);
    }

}

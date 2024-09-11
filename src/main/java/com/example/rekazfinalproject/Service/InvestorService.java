package com.example.rekazfinalproject.Service;

import com.example.rekazfinalproject.Api.ApiException;
import com.example.rekazfinalproject.DTO.InvestorDTO;
import com.example.rekazfinalproject.Model.Investor;
import com.example.rekazfinalproject.Model.User;
import com.example.rekazfinalproject.Repository.InvestorRepository;
import com.example.rekazfinalproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestorService {

    //*** All CRUD Done by Danah ****
    private final InvestorRepository investorRepository;
    private final UserRepository userRepository;

    public List<Investor> getAllInvestor(){
        return investorRepository.findAll();
    }
    public void registerInvestor(InvestorDTO investorDTO) {
        User user = new User();
        user.setUsername(investorDTO.getUsername());
        user.setPassword(investorDTO.getPassword());
        user.setEmail(investorDTO.getEmail());
        user.setRole("INVESTOR");

        Investor investor = new Investor();
        investor.setCommercialRegister(investorDTO.getCommercialRegister());
        investor.setNumOfInvest(investorDTO.getNumOfInvest());
        investor.setInvestorSectors(investorDTO.getInvestorSectors());
        investor.setCreatedAt(LocalDate.now());
        investor.setUser(user);

        user.setInvestor(investor);

        userRepository.save(user);
    }

    public void updateInvestor(Integer id, InvestorDTO investorDTO) {
        Investor investor = investorRepository.findInvestorById(id);
        if (investor == null) {
            throw new ApiException("investor not found");
        }

        investor.setCommercialRegister(investorDTO.getCommercialRegister());
        investor.setNumOfInvest(investorDTO.getNumOfInvest());
        investor.setInvestorSectors(investorDTO.getInvestorSectors());
        investorRepository.save(investor);

        User user = investor.getUser();
        user.setUsername(investorDTO.getUsername());
        user.setPassword(investorDTO.getPassword());
        user.setEmail(investorDTO.getEmail());
        userRepository.save(user);
    }

    public void deleteInvestor(Integer id) {
        User investor = userRepository.findUserById(id);
        if (investor == null) {
            throw new ApiException("investor not found");
        }
        userRepository.delete(investor);
    }
}

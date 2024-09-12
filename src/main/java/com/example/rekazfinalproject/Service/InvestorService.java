package com.example.rekazfinalproject.Service;

import com.example.rekazfinalproject.Api.ApiException;
import com.example.rekazfinalproject.DTO.InvestorDTO;
import com.example.rekazfinalproject.Model.Bid;
import com.example.rekazfinalproject.Model.Investor;
import com.example.rekazfinalproject.Model.Project;
import com.example.rekazfinalproject.Model.User;
import com.example.rekazfinalproject.Repository.BidRepository;
import com.example.rekazfinalproject.Repository.InvestorRepository;
import com.example.rekazfinalproject.Repository.ProjectRepository;
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
    private final ProjectRepository projectRepository;
    private final BidRepository bidRepository;

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

    // Investor add bid in specific project , made by suliman

    public void addBid( Integer investorId , Integer projectId , Bid bid) {

        Project project = projectRepository.findProjectById(projectId);

        if(project==null){
            throw new ApiException("Project not found");
        }

        Investor investor = investorRepository.findInvestorById(investorId);

        if(investor==null){
            throw new ApiException("Investor not found");
        }

        // investor cannot make many bids to same project

        for(Bid bid1 : project.getBid()) {
            if(bid1.getInvestor()==investor){
                throw new ApiException("you have already bid in this project");
            }
        }

        // when bid is approved other investors cannot add bids

        if(project.getStatus().equalsIgnoreCase("Closed")){
            throw new ApiException("this project has a approved bid");
        }

        bid.setInvestor(investor);

        bid.setProject(project);

        bidRepository.save(bid);
    }


    // Investor edit his bid , made by suliman

    public void editBid ( Integer investorId , Integer id , Bid bid) {

        Investor investor = investorRepository.findInvestorById(investorId);

        if(investor==null){
            throw new ApiException("Investor not found");
        }


        Bid bid1 = bidRepository.findBidById(id);

        if(bid1==null){
            throw new ApiException("Bid not found");
        }

        if(bid1.getStatus().equalsIgnoreCase("Approved")){
            throw new ApiException("Approved Bid cannot be edited");
        }

        if(bid1.getInvestor()!=investor){
            throw new ApiException("this bid belong to another investor");
        }

        bid1.setBudget(bid.getBudget());
        bid1.setDeadline(bid.getDeadline());
        bid1.setDescription(bid.getDescription());

        bidRepository.save(bid1);
    }

}

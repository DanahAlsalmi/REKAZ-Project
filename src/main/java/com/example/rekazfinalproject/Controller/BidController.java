package com.example.rekazfinalproject.Controller;


import com.example.rekazfinalproject.Model.Bid;
import com.example.rekazfinalproject.Service.BidService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bid")


public class BidController {

    private final BidService bidService;

    @GetMapping("/get-all-bids")
    public ResponseEntity getAllBids() {
        return ResponseEntity.status(200).body(bidService.findAllBids());
    }

    @PostMapping("/add-bid/{projectid}")
    public ResponseEntity addBid(@PathVariable int investorid  , @PathVariable int projectid , @Valid @RequestBody Bid bid) {
        bidService.addBid(investorid,projectid,bid);
        return ResponseEntity.status(200).body("Bid added");
    }

    @PutMapping("/update-bid/{id}")
    public ResponseEntity updateBid(@PathVariable int id, @Valid  @RequestBody Bid bid) {
        bidService.updateBid(id,bid);
        return ResponseEntity.status(200).body("Bid updated");
    }

    @DeleteMapping("/delete-bid/{id}")
    public ResponseEntity deleteBid(@PathVariable int id) {
        bidService.deleteBid(id);
        return ResponseEntity.status(200).body("Bid deleted");
    }
}

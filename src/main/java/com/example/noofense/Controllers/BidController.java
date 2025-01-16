package com.example.noofense.Controllers;

import com.example.noofense.Models.Bid;
import com.example.noofense.Services.Impl.BidServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/main/bids")
@RequiredArgsConstructor
@Slf4j
public class BidController {
    private final BidServiceImpl bidServiceImpl;

    @GetMapping
    private String getAllBids(Model model) {
         List<Bid> bids = bidServiceImpl.listBids();
         model.addAttribute("bids", bids);
         log.info(bids.toString());
         return "bids";
    }

    @PostMapping
    private ResponseEntity<?> createBid() {
        return ResponseEntity.ok("Create");
    }
}

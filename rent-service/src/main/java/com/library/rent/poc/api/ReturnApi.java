package com.library.rent.poc.api;


import com.library.rent.poc.output.StockOutput;
import com.library.rent.poc.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/rent")
public class ReturnApi {

    private RentService rentService;

    public ReturnApi(RentService rentService) {
        this.rentService = rentService;
    }

    @DeleteMapping("/{receipt}")
    public ResponseEntity<?> returnBook(@PathVariable String receipt) {
        return ok(new StockOutput(rentService.returnBook(receipt)));
    }
}

package com.library.rent.poc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.rent.poc.output.ReceiptOutput;
import com.library.rent.poc.output.RentOutput;
import com.library.rent.poc.service.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/rent")
public class RentApi {

    private RentService rentService;
    private ObjectMapper objectMapper;

    public RentApi(RentService rentService, ObjectMapper objectMapper) {
        this.rentService = rentService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/{cpf}/{bookTitle}")
    public ResponseEntity<?> rentABook(@PathVariable String cpf, @PathVariable String bookTitle) throws UnexpectedException {
        String receipt = rentService.rentABook(cpf, bookTitle);
        return ok(new ReceiptOutput(receipt));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getRentsByCpf(@PathVariable String cpf) {
        List<RentOutput> rents = rentService.getRentsByCpf(cpf).stream().map(rent -> objectMapper.convertValue(rent, RentOutput.class)).collect(Collectors.toList());
        return ok(rents);
    }
}

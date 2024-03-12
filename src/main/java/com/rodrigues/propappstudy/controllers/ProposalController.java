package com.rodrigues.propappstudy.controllers;

import com.rodrigues.propappstudy.dtos.proposal.ProposalRequestDto;
import com.rodrigues.propappstudy.dtos.proposal.ProposalResponseDto;
import com.rodrigues.propappstudy.services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @PostMapping
    ResponseEntity<ProposalResponseDto> save (@RequestBody ProposalRequestDto requestDto){
        ProposalResponseDto responseDto = service.createProposal(requestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri())
                .body(responseDto);
    }

    @GetMapping
    ResponseEntity<List<ProposalResponseDto>> allProposal (){
        return ResponseEntity.ok(service.allProposal());
    }
}

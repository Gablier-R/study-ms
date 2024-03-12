package com.rodrigues.propappstudy.services;

import com.rodrigues.propappstudy.dtos.proposal.ProposalRequestDto;
import com.rodrigues.propappstudy.dtos.proposal.ProposalResponseDto;
import com.rodrigues.propappstudy.entity.Proposal;
import com.rodrigues.propappstudy.mapper.ProposalMapper;
import com.rodrigues.propappstudy.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository repository;

    public ProposalResponseDto createProposal (ProposalRequestDto requestDto){
        Proposal proposal = ProposalMapper.INSTANCE.convertDtoToProposal(requestDto);
        repository.save(proposal);

        return ProposalMapper.INSTANCE.convertEntityToDto(proposal);
    }

    public List<ProposalResponseDto> allProposal(){
        return ProposalMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
    }
}

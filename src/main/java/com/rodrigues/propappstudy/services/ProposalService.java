package com.rodrigues.propappstudy.services;

import com.rodrigues.propappstudy.dtos.proposal.ProposalRequestDto;
import com.rodrigues.propappstudy.dtos.proposal.ProposalResponseDto;
import com.rodrigues.propappstudy.entity.Proposal;
import com.rodrigues.propappstudy.mapper.ProposalMapper;
import com.rodrigues.propappstudy.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private NotificationRabbitService notificationRabbitService;

    private String exchange;

    public ProposalService( @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
        this.exchange = exchange;
    }

    public ProposalResponseDto createProposal (ProposalRequestDto requestDto){
        Proposal proposal = ProposalMapper.INSTANCE.convertDtoToProposal(requestDto);
        repository.save(proposal);

        notifyRabbitMQ(proposal);

        return ProposalMapper.INSTANCE.convertEntityToDto(proposal);
    }

    private void notifyRabbitMQ (Proposal proposal){
        try {
            notificationRabbitService.notify(proposal,exchange);

        }catch (RuntimeException ignored){
            proposal.setIntegrated(false);
            repository.save(proposal);
        }
    }

    public List<ProposalResponseDto> allProposal(){
        return ProposalMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
    }
}

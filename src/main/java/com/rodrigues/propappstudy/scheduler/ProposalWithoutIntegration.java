package com.rodrigues.propappstudy.scheduler;

import com.rodrigues.propappstudy.repositories.ProposalRepository;
import com.rodrigues.propappstudy.services.NotificationRabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ProposalWithoutIntegration {

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private NotificationRabbitService notificationRabbitService;

    private String exchange;

    private final Logger logger =  LoggerFactory.getLogger(ProposalWithoutIntegration.class);

    public ProposalWithoutIntegration( @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void searchProposalWithoutIntegration(){
        repository.findAllByIsIntegratedIsFalse().forEach(proposal -> {
            try {
                notificationRabbitService.notify(proposal, exchange);
                proposal.setIntegrated(true);
                repository.save(proposal);

            }catch (RuntimeException exception){
                logger.error(exception.getMessage());
            }
        });
    }
}

package com.rodrigues.propappstudy.dtos.proposal;

public record ProposalResponseDto(
        Long id,
        String name,
        String lastName,
        String cpf,
        String phone,
        Double income,
        String requestedAmount,
        int paymentTerm,
        Boolean isApproved,
        String observation
) {
}

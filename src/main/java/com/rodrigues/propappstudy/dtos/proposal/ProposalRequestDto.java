package com.rodrigues.propappstudy.dtos.proposal;

public record ProposalRequestDto(
        String name,
        String lastName,
        String cpf,
        String phone,
        Double income,
        Double requestedAmount,
        int paymentTerm
) {
}

package com.synectiks.pref.service.mapper;

import com.synectiks.pref.domain.*;
import com.synectiks.pref.service.dto.BankAccountsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BankAccounts} and its DTO {@link BankAccountsDTO}.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public interface BankAccountsMapper extends EntityMapper<BankAccountsDTO, BankAccounts> {

    @Mapping(source = "branch.id", target = "branchId")
    BankAccountsDTO toDto(BankAccounts bankAccounts);

    @Mapping(source = "branchId", target = "branch")
    BankAccounts toEntity(BankAccountsDTO bankAccountsDTO);

    default BankAccounts fromId(Long id) {
        if (id == null) {
            return null;
        }
        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setId(id);
        return bankAccounts;
    }
}

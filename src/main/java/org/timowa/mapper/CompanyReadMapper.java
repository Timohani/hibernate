package org.timowa.mapper;

import org.timowa.dto.CompanyReadDto;
import org.timowa.entity.Company;

public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {
    @Override
    public CompanyReadDto mapFrom(Company company) {
        return new CompanyReadDto(company.getId(),
                company.getName());
    }
}

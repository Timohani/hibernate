package org.timowa.mapper;

import lombok.RequiredArgsConstructor;
import org.timowa.dao.CompanyRepository;
import org.timowa.dto.UserCreateDto;
import org.timowa.entity.User;

@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User mapFrom(UserCreateDto userCreateDto) {
        return User.builder()
                .personalInfo(userCreateDto.personalInfo())
                .username(userCreateDto.username())
                .role(userCreateDto.role())
                .company(companyRepository.findById(userCreateDto.companyId())
                        .orElseThrow(IllegalArgumentException::new))
                .build();
    }
}

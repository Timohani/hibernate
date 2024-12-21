package org.timowa.mapper;

import lombok.RequiredArgsConstructor;
import org.timowa.dto.UserReadDto;
import org.timowa.entity.User;

@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto mapFrom(User user) {
        return new UserReadDto(user.getId(),
                user.getPersonalInfo(),
                user.getUsername(),
                user.getRole(),
                companyReadMapper.mapFrom(user.getCompany()));
    }
}

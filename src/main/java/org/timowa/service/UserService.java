package org.timowa.service;

import lombok.RequiredArgsConstructor;
import org.timowa.dao.UserRepository;
import org.timowa.dto.UserCreateDto;
import org.timowa.dto.UserReadDto;
import org.timowa.mapper.UserCreateMapper;
import org.timowa.mapper.UserReadMapper;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;

    public Long create(UserCreateDto userCreateDto) {

        var userEntity = userCreateMapper.mapFrom(userCreateDto);
        return userRepository.save(userEntity).getId();
    }

    public boolean delete(Long id) {
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(id));
        return maybeUser.isPresent();
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::mapFrom);
    }
}

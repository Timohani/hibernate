package org.timowa.dto;

import org.timowa.entity.PersonalInfo;
import org.timowa.entity.Role;

public record UserReadDto(Long id,
                          PersonalInfo personalInfo,
                          String username,
                          Role role,
                          CompanyReadDto company) {

}

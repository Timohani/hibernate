package org.timowa.dto;

import org.timowa.entity.PersonalInfo;
import org.timowa.entity.Role;

public record UserCreateDto(PersonalInfo personalInfo,
                            String username,
                            Role role,
                            Integer companyId) {
}

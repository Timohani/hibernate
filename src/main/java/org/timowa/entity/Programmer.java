package org.timowa.entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Programmer extends User {
    private Language language;

    @Builder
    public Programmer(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, List<UserChat> userChats, Language language) {
        super(id, username, personalInfo, role, company, profile, userChats);
        this.language = language;
    }
}

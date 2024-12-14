package org.timowa.entity;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager extends User {
    private String project;

    @Builder
    public Manager(Long id, String username, PersonalInfo personalInfo, Role role, Company company, Profile profile, List<UserChat> userChats, String project) {
        super(id, username, personalInfo, role, company, profile, userChats);
        this.project = project;
    }
}

package org.timowa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "FindUserByNameAndCompany", query = """
                select u from User u
                join u.company c
                where u.personalInfo.firstname = :firstname
                and c.name = :company
                """)

@Data
@ToString(exclude = {"profile", "company", "userChats", "payments"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Embedded //Optional (Необязательно)
    private PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToMany
    private List<Payment> payments;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();
}

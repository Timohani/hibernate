package org.timowa.entity;

import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = {"personalInfo", "company"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @Embedded //Optional (Необязательно)
    private PersonalInfo personalInfo;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
}

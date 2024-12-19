package org.timowa.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
}

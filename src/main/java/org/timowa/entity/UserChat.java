package org.timowa.entity;


import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_chat")
public class UserChat extends AuditableEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    public void setUser(User user) {
        this.user = user;
        user.getUserChats().add(this);
    }
    public void setChat(Chat chat) {
        this.chat = chat;
        chat.getUserChats().add(this);
    }
}

package com.spring.mmm.domain.users.infra;

import com.spring.mmm.common.event.Events;
import com.spring.mmm.domain.mbtis.domain.MukBTIResultEntity;
import com.spring.mmm.domain.mukgroups.domain.MukboType;
import com.spring.mmm.domain.users.controller.request.UserJoinRequest;
import com.spring.mmm.domain.mukgroups.domain.MukboEntity;
import com.spring.mmm.domain.users.event.UserDeletedEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email
    private String email;

    private String nickname;
    private String password;

    @Column(name = "is_recorded")
    private Boolean isRecorded;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.PERSIST)
    private MukboEntity mukboEntity;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    private List<MukBTIResultEntity> mukBTIResultEntities;

    public static UserEntity create(UserJoinRequest userJoinRequest, String encodedPW, Long groupId) {

        UserEntity user = UserEntity.builder()
            .email(userJoinRequest.getEmail())
            .nickname(userJoinRequest.getNickname())
            .password(encodedPW)
            .isRecorded(false)
            .build();
        user.mukboEntity = MukboEntity.createMukbo(userJoinRequest.getNickname(), MukboType.HUMAN, groupId, user);
        return user;
    }

    public void recordMukBTIResult(){
        this.isRecorded = true;
    }

    public void modify(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public static UserEntity createWithOnlyUserId(Long userId){
        return UserEntity.builder()
                .id(userId)
                .build();
    }

    public void deleteUser() {
        Events.raise(UserDeletedEvent.create(this.id));
    }

    @Override
    public String toString() {
        return "UserEntity{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", nickname='" + nickname + '\'' +
            '}';
    }
}

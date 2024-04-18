package com.spring.mmm.domain.muklogs.domain;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import com.spring.mmm.domain.muklogs.controller.response.MuklogResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "muklog")
@Entity
public class MuklogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muklog_id")
    private Long muklogId;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private Instant createdAt;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukgroup_id")
    private MukgroupEntity mukgroupEntity;


    public static MuklogEntity create(MukgroupEntity mukGroupEntity, String content){
        return MuklogEntity.builder()
                .mukgroupEntity(mukGroupEntity)
                .content(content)
                .createdAt(Instant.now())
                .build();
    }

    @Override
    public String toString() {
        return "MuklogEntity{" +
                "muklogId=" + muklogId +
                ", createdAt=" + createdAt +
                ", content='" + content + '\'' +
                '}';
    }
}

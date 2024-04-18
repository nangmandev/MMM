package com.spring.mmm.domain.mukjuks.domain;

import com.spring.mmm.domain.mukgroups.domain.MukgroupEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mukjuk")
@Entity
public class MukjukEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mukjuk_id")
    private Long mukjukId;

    @Column(name = "name")
    private String name;

    @Column(name = "context")
    private String context;

    @Column(name = "image_src")
    private String imageSrc;

    @OneToMany(mappedBy = "mukjukEntity", cascade = CascadeType.REMOVE)
    private List<MukgroupMukjukEntity> mukGroupMukJukEntities;

    @OneToMany(mappedBy = "mukjukEntity")
    private List<MukgroupEntity> mukGroupEntities;

    @Override
    public String toString() {
        return "MukjukEntity{" +
                "mukjukId=" + mukjukId +
                ", name='" + name + '\'' +
                ", context='" + context + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", mukGroupMukJukEntities=" + mukGroupMukJukEntities +
                '}';
    }
}

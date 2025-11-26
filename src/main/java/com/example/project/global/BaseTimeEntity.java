package com.example.project.global;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseTimeEntity {
        @CreatedDate
        private LocalDateTime createAt; //Entity가 생성되어 저장될 때 시간이 자동 저장

        @LastModifiedDate
        private LocalDateTime updatedAt; //조회한 Entity의 값을 변경할 때 시간이 자동 저장
}

package kr.co.uxn.domain.blog;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@EntityListeners(AuditingEntityListener.class)
@DynamicInsert//nullable 인 컬럼을 DDL 에서 default 값으로 변경 가능하도록
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @CreatedDate
    @Column(name = "created_at")
    private OffsetDateTime createdAt;//localtime + timezone

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @LastModifiedDate
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;//localtime + timezone

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = OffsetDateTime.now();
    }
}

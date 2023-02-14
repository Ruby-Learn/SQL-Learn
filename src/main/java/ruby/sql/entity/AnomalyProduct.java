package ruby.sql.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AnomalyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 작가 이름. 도서 상품이 아닌 경우에는 NULL 입력
    private String author;
    // 아티스트 이름. 앨범 상품이 아닌 경우에는 NULL 입력
    private String artist;

    @Enumerated(EnumType.STRING)
    private ProductType productType;
}

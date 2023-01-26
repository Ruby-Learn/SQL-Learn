package ruby.sql.dml.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ruby.sql.dml.member.dto.MemberSearchDto;
import ruby.sql.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    /**
     * select *
     * from member
     */
    @Test
    void selectAll() {
        memberRepository.findAll();
    }


    /**
     * select *
     * from member
     * where condition
     */
    @Test
    void selectWhere() {
        MemberSearchDto memberSearchDto = new MemberSearchDto();

        memberRepository.findByWhere(memberSearchDto);
    }

    /**
     * select *
     * from member
     * limit A, B
     *
     * select count(*)
     * from member
     *
     * A : offset. 해당 숫자 이후로 조회
     * B : limit. 최대 row 조회 개수
     */
    @Test
    void selectPage() {
        IntStream.range(0, 55)
                .forEach(idx -> memberRepository.save(new Member()));

        int pageNum = 2;
        int pageSize = 10;

        memberRepository.findByPage(pageNum, pageSize);
    }
}
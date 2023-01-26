package ruby.sql.dml.member;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import ruby.sql.dml.member.dto.MemberSearchDto;
import ruby.sql.entity.Member;

import java.util.List;

import static ruby.sql.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findByWhere(MemberSearchDto memberSearchDto) {
        return queryFactory.selectFrom(member)
                .where(
                        member.name.contains(memberSearchDto.getInput())
                                .or(member.email.contains(memberSearchDto.getInput())),
                        member.grade.eq(memberSearchDto.getGrade()),
                        member.salary.loe(memberSearchDto.getSalary())
                )
                .fetch();
    }

    @Override
    public Page<Member> findByPage(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        List<Member> members = queryFactory.selectFrom(member)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(member.count())
                .from(member);

        return PageableExecutionUtils.getPage(members, pageable, countQuery::fetchOne);
    }
}

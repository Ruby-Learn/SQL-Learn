package ruby.sql.dml.member;

import org.springframework.data.domain.Page;
import ruby.sql.dml.member.dto.MemberSearchDto;
import ruby.sql.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findByWhere(MemberSearchDto memberSearchDto);

    Page<Member> findByPage(int pageNum, int pageSize);
}

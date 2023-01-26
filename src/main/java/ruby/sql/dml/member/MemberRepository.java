package ruby.sql.dml.member;

import org.springframework.data.jpa.repository.JpaRepository;
import ruby.sql.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
}

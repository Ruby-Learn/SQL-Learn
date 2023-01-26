package ruby.sql.dml.member.dto;

import lombok.Data;
import ruby.sql.entity.enums.Grade;
import ruby.sql.entity.enums.Position;

@Data
public class MemberSearchDto {

    private String input;
    private Grade grade;
    private Position position;
    private Integer salary;
}

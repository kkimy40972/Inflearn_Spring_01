package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{


    private final JdbcTemplate jdbcTemplate; // 인젝션은 받을 수 없음

    //@Autowired // 생성자가 딱 하나만 있으면 @Autowired 생략할 수 있음
    public JdbcTemplateMemberRepository(DataSource dataSource){ // 스프링이 자동으로 dataSource 를 인젝션 해줌
        jdbcTemplate = new JdbcTemplate(dataSource); // 인젝션 받은 dataSource 여기에 넣어주기
    }


    @Override
    public Member save(Member member) {

        // SimpleJdbcInsert : jdbcTemplate 를 넘겨서 만듦
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // 쿼리를 만들 필요가 없음
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        // 여기까지 코드로 인서트 구문이 만들어짐

        // executeAndReturnKey 사용해서 Key 를 받고
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue()); // setId() 이용해서 member 에 넣어줌

        return member;
    }


    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id= ?", memberRowMapper(), id); // 결과를 RowMapper 로 받아오기
        return result.stream().findAny();
    }


    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name= ?", memberRowMapper(), name); // 결과를 RowMapper 로 받아오기
        return result.stream().findAny();
    }


    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }


    private RowMapper<Member> memberRowMapper(){
    /*
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };
    */
        // 람다식으로 변경
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}

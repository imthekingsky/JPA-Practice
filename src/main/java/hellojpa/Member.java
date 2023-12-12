package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // JPA가 로딩될때 사용되는것으로 인식한다.
public class Member {

    @Id // JPA에게 PK가 뭔지 알려준다
    private Long id;
    private String name;

    // JPA는 기본 생성자가 있어야 한다.
    public Member(){}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



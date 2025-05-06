package mapping;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private long id;

    @Column(name = "USER_NAME")
    private String username;


    @ManyToOne(fetch = FetchType.EAGER) //아무것도 지정 안해주면 (fetch = FetchType.EAGER)가 기본 값이다.
    @JoinColumn(name = "TEAM_ID") //외래키를 잡아준다.
    //여러 멤버는 하나의 팀에 속한다.
    private Team team; //Member의 team이 연관관계의 주인이다.
    //외래키가 있는 곳을 연관관계의 주인으로 정해주자.


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

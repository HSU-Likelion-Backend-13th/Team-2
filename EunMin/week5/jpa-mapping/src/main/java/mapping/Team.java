package mapping;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private long teamId;

    @Column(name = "TEAM_NAME")
    private String name;

    //mappedBy는 연관관계의 주인을 정해줌
    //하나의 팀은 여러 멤버를 가진다.
    @OneToMany(mappedBy = "team") //Member 클래스의 team과 연결되어 있음
    private List<Member> members = new ArrayList<>(); //Team의 members는 가짜주인 애는 읽기만 가능함.

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}

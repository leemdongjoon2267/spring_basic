package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberJdbcRepository;
import com.example.demo.repository.MemberMybatisRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;

@Service
public class MemberService {

//    service에서 repository를 주입 받기 위해서, Autowired를 사용
//    SpringDataJpa를 사용한 repository
    @Autowired
    private MemberRepository memberRepository;

//    mybatis를 사용한 repository
//    jpa와 함께 사용할수도 있다. 복잡한 service logic 또는 heavy한 쿼리가 있을 경우
//    jpa로는 한계가 있으므로, 현업에서는 mybatis와 jpa를 섞어 사용하기도 한다.
    @Autowired
    private MemberMybatisRepository memberMybatisRepository;
    @Autowired
    private MemberJdbcRepository memberJdbcRepository;

//    회원가입(등록)
    public void create(Member member) throws SQLException {
//        System.out.println("memberJDBCRepository test");
        memberRepository.save(member);
    }

//    회원목록조회
//    memberRepository.findAll()의 기본 return 타입은 List<해당객체>
    public List<Member> findAll(){
        List<Member> members = memberRepository.findAll();
//        for(Member m :members){
//            System.out.println(m.getName());
//            System.out.println(m.getEmail());
//            System.out.println(m.getPassword());
//        }
        return members;
    }

    public Member findById(Long myId){
        Member member = memberRepository.findById(myId).orElse(null);
        return member;
    }

//    회원수정
    public void update(Member member) throws Exception {
//        save는 이미 존재하는 pk(id)이 있으면 update로 동작, id값이 없으면 insert로 동작
        Member member1 = memberRepository.findById(member.getId()).orElse(null);
        if(member1 == (null)){
            throw new Exception();
        }else {
            member1.setName(member.getName());
            member1.setEmail(member.getEmail());
            member1.setPassword(member.getPassword());
            memberRepository.save(member1);

        }
    }
}

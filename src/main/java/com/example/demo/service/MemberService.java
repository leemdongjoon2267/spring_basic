package com.example.demo.service;

import antlr.ASTNULLType;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

//    service에서 repository를 주입 받기 위해서, Autowired를 사용
    @Autowired
    private MemberRepository memberRepository;

//    회원가입(목록)
    public void create(Member member){
        memberRepository.save(member);
    }

//    회원목록조회
//    memberRepository.findAll()의 기본 return 타입은 List<해당객체>
    public List<Member> findAll(){
        List<Member> members = memberRepository.findAll();
        return members;
    }

    public Member findById(Long id){
        Member member = memberRepository.findById(id).orElse(null);
        return member;
    }


}

package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller("/json")
public class MemberJsonController {


    @Autowired
    private MemberService memberService;

    @PostMapping("json/members/new")
    @ResponseBody
    public String memberCreate(@RequestParam(value = "name")String myName,
                               @RequestParam(value = "email")String email,
                               @RequestParam(value = "password")String password
    ) throws SQLException {
        Member member1 = new Member();
        member1.setName(myName);
        member1.setName(email);
        member1.setName(password);
//        Memebr는 class를 new하여 객체를 만드는 반면에
//        MemberService는 객체를 만들지 않고, 바로 사용하고 있다.
//        이는 MemberService는 Component를 통해 싱글톤이로 반들어져 있기 때문
//        싱글톤으로 만들어진 Component는 객체를 생성하는것이 아니라 주입을 받아 사용
        memberService.create(member1);
        return "ok";
    }


    @GetMapping("json/members")
    @ResponseBody
    public List<Member> memberFindAll(){
        List<Member> members = memberService.findAll();
        return members;
    }

    @GetMapping("json/member")
    @ResponseBody
    public Member memberFindById(@RequestParam(value = "id")Long myId){
        Member member = memberService.findById(myId);
        return member;
    }


}

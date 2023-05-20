package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    ){
        Member member1 = new Member();
        member1.setName(myName);
        member1.setName(email);
        member1.setName(password);
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

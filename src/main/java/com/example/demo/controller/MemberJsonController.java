package com.example.demo.controller;


import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller()
public class MemberJsonController {

    //    MemberService를 주입
    @Autowired
    private MemberService memberService;


    @PostMapping("json/members/new")
    @ResponseBody
//    input값을 form-data로 받는 형식
    public String memberCreate(@RequestParam(value = "name")String myName,
                               @RequestParam(value = "email")String email,
                               @RequestParam(value = "password")String password){
//        Member객체를 만들어서 MemberService 매개변수로 전달
        Member member1 = new Member();
        member1.setName(myName);
        member1.setEmail(email);
        member1.setPassword(password);
        memberService.create(member1);
        return "ok";
    }


    //    목록조회
    @GetMapping("json/members")
    @ResponseBody // @ResponseBody가 있어야 json으로 나갈 수 있음
    public List<Member> memberFindAll(){
        List<Member> members = memberService.findAll();
        return members;
    }

    @GetMapping("json/member")
    @ResponseBody
    //@ResponseBody가 있고 return타입이 String이면 문자로 리턴
    // @ResponseBody있고 return타입이객체면 json으로 리턴
    public Member memberFindById(@RequestParam(value = "id")Long myId){
        Member member = memberService.findById(myId);
        return member;
    }

}

package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

//    MemberService를 주입
    @Autowired
    private MemberService memberService;


//    PostMapping을 통해 MemberService호출하는 Method 생성
//    @PostMapping("members")
//    @ResponseBody
////    input값을 json으로 받는 형식
//    public String memberCreate(@RequestBody Member member){
////        Member객체를 만들어서 MemberService 매개변수로 전달
//        Member member1 = new Member();
//        member1.setName("hong"); // member1.setName(getName());
//        member1.setEmail("hong@naver.com"); // member1.setName(getEmail());
//        member1.setPassword("1234"); // member1.setName(getPassword());
//        memberService.create(member1);
//        return "ok";
//}

    @PostMapping("members")
    @ResponseBody
//    input값을 form-data로 받는 형식
    public String memberCreate(@RequestParam(value = "name")String myName,
                               @RequestParam(value = "name")String myEmail,
                               @RequestParam(value = "name")String myPassword){
//        Member객체를 만들어서 MemberService 매개변수로 전달
        Member member1 = new Member();
        member1.setName("hong"); // member.setName(getName());
        member1.setEmail("hong@naver.com"); // member.setName(getEmail());
        member1.setPassword("1234"); // member.setName(getPassword());
        memberService.create(member1);
        return "ok";
    }

    @GetMapping("members")
    @ResponseBody
    public List<Member> memberFindAll(){
        List<Member> members = memberService.findAll();
        return members;
    }

    @GetMapping("member")
    @ResponseBody
    public Member memberFindById(@RequestParam(value = "id")Long myId){
        Member member = memberService.findById(myId);
        return member;
    }

    @GetMapping("members/new")
    public String memberCreateForm(){
        return "member/member-register";
    }

    @PostMapping("members/new")
    @ResponseBody
    public String memberCreate(@RequestParam(value = "name")String myName,
                               @RequestParam(value = "email")String myEmail,
                               @RequestParam(value = "password")String myPassword
                                ){
        Member member1 = new Member();
    }
}

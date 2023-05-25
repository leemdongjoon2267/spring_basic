package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MemberController {

//    MemberService를 주입
    @Autowired
    private MemberService memberService;

    //PostMapping을 통해 MemberService호출하는 Method 생성
//    @PostMapping("members")
//    @ResponseBody
////    input값을 json으로 받는 형식
//    public String memberCreate(@RequestBody Member member) { // member는 매겨변수로 들어올 member
////        Member객체를 만들어서  MemberService 매개변수로 전달
//        Member member1 = new Member(); // member1은 최종적으로 들어갈 member1
//        member.setName(member.getName());
//        member.setEmail(member.getEmail());
//        member.setPassword(member.getPassword());
//        memberService.create(member1);
//        return "ok";
//}

    @GetMapping("members/new") //@PostMapping(members)랑 겹쳐도 상관없음
    @ResponseBody
    public String memberCreateForm(){
        return "member/member-register";
    }

    @PostMapping("members/new")
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
    @GetMapping("members") //@PostMapping(members)랑 겹쳐도 상관없음
    public String memberFindAll(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("memberList", members);
        return "member/member-list";
    }

    @GetMapping("member")
    //@ResponseBody가 있고 return타입이 String이면 문자로 리턴
    // @ResponseBody있고 return타입이객체면 json으로 리턴
    public String memberFindById(@RequestParam(value = "id")Long myId, Model model){
        Member member = memberService.findById(myId);
        model.addAttribute("member", member);
        return "member/member-detail";
    }

    @GetMapping("member-json")
    @ResponseBody
    public Member memberFindByJson(@RequestParam(value = "id")Long myId){
        Member member = memberService.findById(myId);
        return member;
    }

}

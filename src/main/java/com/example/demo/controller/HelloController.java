package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;


@Controller
public class HelloController {

//    http:/localhost:8080/hello로 요청시 hello메서드에서 처리
//    http는 국제 통신프로토콜이다. https는 (s-secure) 보안이 강화된 통신프로토콜
//    port란 한 IP내에 여러 프로그램을 구분짓는 단위. 집주소가 IP, 각집의 방문이 port
//    data만을 return할때는 ResponseBody를 사용한다.
    @GetMapping("hello")
    @ResponseBody
    public String hello(){

        return "hello world";
    }

    @GetMapping("hello2")
    @ResponseBody
    public String hello2(){

        return "hello2 world";
    }

//    jsp/thymeleaf같은 템플릿엔진을 사용하여 화면을 return할때는 ResponseBody를 사용하면 안된다.
//    그리고 Model이라는 객체에 data를 담아 return xxx를 하여 xxx.html파일로 데이터를 보낸다.
    @GetMapping("hello-thymeleaf")
    public String hello2(Model model){
        model.addAttribute("getdata","hello2 world");
        return "hello-th";
    }

//    데이터를 첨부시키지않고, 화면만을 렌더링(준다)할 수도 있다.
    @GetMapping("hello-html")
    public String helloHtml(){
        return "hello-get-req";
    }

    @GetMapping("hello-get-form-req")
    public String helloGetformReq(){
        return "hello-post-form-req";
    }

    @PostMapping("hello-post-form-req")
    public String helloPostFormReq(@RequestParam(value="name")String myname,
                                   @RequestParam(value="email")String myemail,
                                   @RequestParam(value="password")String mypassword){
        System.out.println("이름 : " +myname);
        System.out.println("이메일 : " +myemail);
        System.out.println("비밀번호 : " +mypassword);
        return "ok";
    }

//    테스트 할때 localhost:8080/hello-parameter?test=hello
    @GetMapping("hello-parameter")
    @ResponseBody
    public String helloParameter(@RequestParam(value="test")String mytest){
        System.out.println("클라이언트가 보내온 parameter는? " +mytest);
        return "ok";
    }

    @GetMapping("user1")
    @ResponseBody
    public String user1(@RequestParam(value="id")String myId) throws AccessDeniedException {
        if(true){
            throw new AccessDeniedException("권한이 없습니다.");
        }
        System.out.println("ID는? "+myId);

        return "ok";
    }

    @GetMapping("user2")
    @ResponseBody
    public String user2(@PathVariable(value="id")String myId){
        System.out.println("ID는? "+myId);

        return "ok";
    }
//    사용자가 서버로 데이터를 보내는 방식에는 총3가지가 있다.
//    1. ?를 통해 parameter 값을 넣어 보내는 방식 : 대부분 get요청시 사용
//    2. form태그 안에 data를 넣어 보내는 방식 : post요청시 사용
//    (보안이 강화되고, url에 데이터 찍히지 않는다. 그런데, 내부적으로는 ?key1=value1&&key2=value2의 형식을 취한다.
//    3. json 데이터 형식으로 서버로 보내는 방식 :post요청시 사용
//    json데이터란 {"key1":"value1", "key2":"value2"}의 형식을 취하는 데이터이다.
//    현대적인 web서비스에서 대부분의 데이터를 주고 받을때 json을 사용한다.
//    json은 html의 form태그에 넣어서 보내는 방식이 아니다보니,
//    Ajax, react 이런 javascript 프레임워크를 사용하게된다.


    @GetMapping("hello-get-json-reg")
    public String helloGetJsonReg(){
        return "hello-post-json-reg";
    }
    //    json으로 post요청이들어왔을때는 data를꺼내기 위해 RequestBody 사용
    @PostMapping("hello-json")
    @ResponseBody
    public String helloJson(@RequestBody Hello hello){
        System.out.println("이름 : "+hello.getName());
        System.out.println("이메일 : "+hello.getEmail());
        System.out.println("비밀번호 : "+hello.getPassword());
        return "ok";
    }

    //    ResponseBody 어노테이션이 붙어 있고, return타입이 객체이면 spring이 json형태로 변환해준다
    @PostMapping("hello-json-response")
    @ResponseBody
    public GoodBye helloJsonResponse(@RequestBody Hello hello){
        System.out.println("이름 : "+hello.getName());
        System.out.println("이메일 : "+hello.getEmail());
        System.out.println("비밀번호 : "+hello.getPassword());
        GoodBye goodBye1 = new GoodBye();
        goodBye1.setName(hello.getName());
        goodBye1.setEmail(hello.getEmail());
        goodBye1.setComments("Thank you");
        return goodBye1;
    }
}

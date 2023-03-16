package com.ll.basic1.boundedContext.home.controller;

import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// @Controller 의 의미
// 개발자가 스프링부트에게 말한다.
// 아래 있는 HomeController 는 컨트롤러이다.
@Controller
public class HomeController {
    // @GetMapping("/home/main") 의 의미
    // 개발자가 스프링부트에게 말한다.
    // 만약에 /home/main 이런 요청이 오면 아래 메서드를 실행해줘
//    @GetMapping("/home/main")
//    // @ResponseBody 의 의미
//    // 아래 메서드를 실행한 후 그 리턴값을 응답으로 삼아줘
//    @ResponseBody
//    public String showMain() {
//        return "안녕하세요:)";
//    }
//
//    @GetMapping("/home/main2")
//    @ResponseBody
//    public String showMain2() {
//        return "반갑습니다";
//    }
//
//    @GetMapping("/home/main")
//    @ResponseBody
//    public String showMain3(){
//        return "즐겁습니다";
//    }
    private int cnt;
    // 필드 주입
//    @Autowired
//    private MemberService memberService;

    private final MemberService memberService;

    @GetMapping("home/increase")
    @ResponseBody
    public String showM() {
        cnt++;
        return "응답 : " + cnt;
    }

    @GetMapping("home/plus")
    @ResponseBody
    public String showMM(@RequestParam("a") int a, @RequestParam("b") int b) {

        return "응답 : " + (a+b);
    }

    private ArrayList<Person> personArrayList;

    public HomeController(MemberService memberService) {
        cnt =-1;
        personArrayList = new ArrayList<>();
//        memberService = new MemberService();
        this.memberService = memberService;
    }

    @GetMapping("home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, @RequestParam int age) {

        Person person = new Person(name, age);

        personArrayList.add(person);

        return personArrayList.size() + "번째 사람 입력했습니다.";
    }

    @GetMapping("home/people")
    @ResponseBody
    public List allFindPeople() {

        return personArrayList;
    }

    @GetMapping("home/removePerson")
    @ResponseBody
    public String removePeople(@RequestParam int id) {

        for (int i = 0; i < personArrayList.size(); i++) {
            Person p = personArrayList.get(i);
            if (p.getId() == id) {
                personArrayList.remove(i);
                return id+ "번 사람이 삭제되어야 함";
            }
        }

        return "아이디가 없습니다.";
    }

    @GetMapping("home/modifyPerson")
    @ResponseBody
    public String updatePeople(@RequestParam int id , String name, int age) {

        for (int i = 0; i < personArrayList.size(); i++) {
            Person p = personArrayList.get(i);
            if (p.getId() == id) {
                p.setName(name);
                p.setAge(age);
                personArrayList.set(i,p);
                return id+ "번 사람이 수정되었습니다";
            }
        }

        return "아이디가 없습니다.";
    }

    @GetMapping("home/count")
    @ResponseBody
    public int showCookieIncrease(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        int countInCookie =0;

        if (req.getCookies() != null) {
            countInCookie = Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals("count"))
                    .map(cookie -> cookie.getValue())
                    .mapToInt(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }

        int newCountInCookie = countInCookie + 1;

        resp.addCookie(new Cookie("count", newCountInCookie + ""));

        return newCountInCookie;
    }


    @GetMapping("/home/user1")
    @ResponseBody
    public Member showUser1() {
        return memberService.findByUsername("user1");
    }



}
@AllArgsConstructor
@Getter
@Setter
@ToString
class Person{

    private static int lastId;
    private final int id;
    private String name;
    private int age;

    static {
        lastId=0;
    }

    Person(String name, int age) {
        this(++lastId, name, age);
    }

    public void setPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

}


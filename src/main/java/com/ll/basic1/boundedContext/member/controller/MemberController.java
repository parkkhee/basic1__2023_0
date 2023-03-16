package com.ll.basic1.boundedContext.member.controller;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.repository.MemberRepository;
import com.ll.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/login")
    @ResponseBody
    public RsData login(String username, String password, HttpServletResponse resp) {
//        Map<String, Object> rsData = new LinkedHashMap<>() {{
//            put("resultCode", "S-1");
//            put("msg", "%s 님 환영합니다.".formatted(username));
//        }};
        if (username == null || username.trim().length() == 0) {
            return RsData.of("F-3", "username(을)를 입력해주세요.");
        }

        if (password == null || password.trim().length() == 0) {
            return RsData.of("F-4", "password(을)를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);

        if (rsData.isSuccess()) {
//            long memberId = (long) rsData.getData();
//            resp.addCookie(new Cookie("loginedMemberId", memberId + ""));
            Rq rq = new Rq(resp);
            rq.setCookie("name", "홍길동");
            rq.setCookie("age", 23); // long 으로 처리
        }

        return rsData;
    }

    @GetMapping("/member/logout")
    @ResponseBody
    public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
//        if (req.getCookies() != null) {
//            Arrays.stream(req.getCookies())
//                    .filter(cookie -> cookie.getName().equals("loginedMemberId"))
//                    .forEach(cookie -> {
//                        cookie.setMaxAge(0);
//                        resp.addCookie(cookie);
//                    });
//        }
//
//        return RsData.of("S-1", "로그아웃 되었습니다.");

        Rq rq = new Rq(req, resp);
        rq.removeCookie("age");

        return RsData.of("S-1", "로그아웃 되었습니다.");
    }


    @GetMapping("/member/me")
    @ResponseBody
    public RsData whome(HttpServletRequest rqe) {

        long loginedMemberId =0;

        if (rqe.getCookies() != null) {

            loginedMemberId = Arrays.stream(rqe.getCookies()).filter(cookie -> cookie.getName().equals("loginedMemberId"))
                    .map(Cookie::getValue)
                    .mapToLong(Long::parseLong)
                    .findFirst()
                    .orElse(0);

        }
        boolean isLogined = loginedMemberId > 0;

        if (isLogined == false)
            return RsData.of("F-1", "로그인 후 이용해주세요.");

        Member member = memberService.findById(loginedMemberId);


        return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));

    }

    @GetMapping("/member/me2")
    @ResponseBody
    public HashMap whome2(HttpServletRequest rqe) {

        Rq rq = new Rq(rqe);
        String name = rq.getCookie("name", "없음"); // 두번째 인자는 defaultValue
        long age = rq.getCookieAsLong("age", 0);

        HashMap<String, Long> p = new HashMap<>();
        p.put(name, age);

        return p;

    }
}

@AllArgsConstructor
class Rq{
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public Rq(HttpServletResponse resp) {
        this.resp = resp;
    }
    public Rq(HttpServletRequest req) {
        this.req = req;
    }

    public RsData removeCookie(String str){

        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(str))
                    .forEach(cookie -> {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    });
        }

        return RsData.of("S-1", "로그아웃 되었습니다.");

    }

    public void setCookie(String name , String value) {
        resp.addCookie(new Cookie(name,value));
    }

    public void setCookie(String name , long value) {
        resp.addCookie(new Cookie(name,String.valueOf(value)));
    }

    public String getCookie(String name, String defaultV) {

        String nm;

        if (req.getCookies() != null) {

            nm = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals(name))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
            return nm;
        }

        return defaultV;
    }

    public long getCookieAsLong(String age, long num) {

        long n;

        if (req.getCookies() != null) {

            n = Arrays.stream(req.getCookies()).filter(cookie -> cookie.getName().equals(age))
                    .map(Cookie::getValue)
                    .mapToLong(Long::parseLong)
                    .findFirst()
                    .orElse(0);

            return n;
        }



        return num;
    }

}

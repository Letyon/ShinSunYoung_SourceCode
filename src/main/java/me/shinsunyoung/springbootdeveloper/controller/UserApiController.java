package me.shinsunyoung.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dto.AddUserRequest;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);
        return "redirect:/login";  // 로그인 하지 않은 회원의 경우, 마이페이지에 접속할 수 있는 권한이 없기 때문에 로그인 페이지로 리다이렉트
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {      // http 프로토콜의 request 정보를 서블릿에게 전달 // 요청을 보낸 클라이언트에게 응답을 보내기 위해 WAS 에서 생성되어 서블릿에게 전달
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());   // 로그아웃 필터를 통해서 처리(Authentication 객체(현재 인증 처리 된 사용자 정보)를 꺼내서 사용할 수 있는 것)
        return "redirect:/login";
    }

}

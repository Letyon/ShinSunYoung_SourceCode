package me.shinsunyoung.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {    // Model 객체는 컨트롤러에서 데이터를 생성해, 이를 JSP 즉 View 에 전달하는 역할
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);   // examplePerson 객체를 "person" 이름으로 추가
        model.addAttribute("today", LocalDate.now());   // LocalDate.now() 객체를 "today" 이름으로 추가

        return "example";     // view name 인 "example" 을 리턴
    }

    @Setter
    @Getter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}


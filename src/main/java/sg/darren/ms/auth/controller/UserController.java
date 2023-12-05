package sg.darren.ms.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sg.darren.ms.auth.model.user.UserRegisterReqDto;
import sg.darren.ms.auth.model.user.UserResDto;
import sg.darren.ms.auth.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String create(@RequestBody @Valid UserRegisterReqDto dto) {
        userService.create(dto);
        return "success";
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResDto getUserByUserName(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

}

package sg.darren.ms.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sg.darren.ms.auth.model.user.UserCreateReqDto;
import sg.darren.ms.auth.model.user.UserResDto;
import sg.darren.ms.auth.model.user.UserUpdateReqDto;
import sg.darren.ms.auth.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResDto getUserByUserName(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    public UserResDto create(@RequestBody @Valid UserCreateReqDto dto) {
        return userService.create(dto);
    }

    @PutMapping("/{username}")
    public UserResDto update(
            @PathVariable("username") String username,
            @RequestBody @Valid UserUpdateReqDto dto) {
        return userService.updateByUsername(username, dto);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable("username") String username) {
        userService.deleteByUsername(username);
    }

}

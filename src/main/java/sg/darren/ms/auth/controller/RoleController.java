package sg.darren.ms.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.darren.ms.auth.model.role.RoleRegisterReqDto;
import sg.darren.ms.auth.service.RoleService;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    public String create(@RequestBody RoleRegisterReqDto dto) {
        roleService.createRole(dto);
        return "success";
    }

}

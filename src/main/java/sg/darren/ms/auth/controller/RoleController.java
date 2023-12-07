package sg.darren.ms.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sg.darren.ms.auth.model.role.RoleCreateReqDto;
import sg.darren.ms.auth.model.role.RoleResDto;
import sg.darren.ms.auth.model.role.RoleUpdateReqDto;
import sg.darren.ms.auth.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoleResDto> getUsers() {
        return roleService.getRoles();
    }

    @GetMapping(value = "/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RoleResDto getUserByUserName(@PathVariable("roleId") String roleId) {
        return roleService.getRoleByRoleId(roleId);
    }

    @PostMapping
    public RoleResDto create(@RequestBody @Valid RoleCreateReqDto dto) {
        return roleService.create(dto);
    }

    @PutMapping("/{roleId}")
    public RoleResDto update(
            @PathVariable("roleId") String roleId,
            @RequestBody @Valid RoleUpdateReqDto dto) {
        return roleService.updateByRoleId(roleId, dto);
    }

    @DeleteMapping("/{roleId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable("roleId") String roleId) {
        roleService.deleteByRoleId(roleId);
    }

}

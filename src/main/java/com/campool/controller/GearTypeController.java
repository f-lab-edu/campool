package com.campool.controller;

import com.campool.annotation.LoginValidation;
import com.campool.enumeration.Role;
import com.campool.model.GearType;
import com.campool.model.GearTypeRegisterRequest;
import com.campool.model.GearTypeUpdateRequest;
import com.campool.service.GearTypeService;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GearTypeController {

    @NonNull
    private final GearTypeService gearTypeService;

    @LoginValidation
    @GetMapping("/types")
    public List<GearType> getGearTypes() {
        return gearTypeService.getGearTypes();
    }

    @LoginValidation(role = Role.ADMIN)
    @PostMapping("/types")
    public void registerGearType(@Valid GearTypeRegisterRequest gearTypeRegisterRequest) {
        gearTypeService.addGearType(gearTypeRegisterRequest.getName());
    }

    @LoginValidation(role = Role.ADMIN)
    @PatchMapping("/types")
    public void updateGearType(@Valid GearTypeUpdateRequest gearTypeUpdateRequest) {
        gearTypeService.updateByName(gearTypeUpdateRequest.getCurrentName(),
                gearTypeUpdateRequest.getNewName());
    }

    @LoginValidation(role = Role.ADMIN)
    @DeleteMapping("/types/{id}")
    public void deleteGearType(@PathVariable long id) {
        gearTypeService.deleteById(id);
    }

}

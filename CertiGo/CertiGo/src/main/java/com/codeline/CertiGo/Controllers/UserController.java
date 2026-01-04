package com.codeline.CertiGo.Controllers;

import com.codeline.CertiGo.DTOCreateRequest.UserCreateRequest;
import com.codeline.CertiGo.DTOResponse.UserResponse;
import com.codeline.CertiGo.DTOUpdateRequest.UserUpdateRequest;
import com.codeline.CertiGo.Exceptions.CustomException;
import com.codeline.CertiGo.Helper.Constants;
import com.codeline.CertiGo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        try {
            UserCreateRequest.validateUserCreateRequested(userCreateRequest);
            UserResponse createdUser = userService.saveUser(userCreateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreateRequest);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //display all courses
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> response = userService.getAllUsers().stream()
                .map(UserResponse::entityToDTOResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) throws CustomException {
        UserResponse userResponse = UserResponse.entityToDTOResponse(userService.getUserById(id));
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    //update user
    @PutMapping("/{id}")
    public String updateUser(@RequestBody UserUpdateRequest updateObjFromUser) throws CustomException {
        return userService.updateUser(updateObjFromUser);
    }

    //delete user by id
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) throws Exception {
        userService.deleteUser(id);
        return Constants.SUCCESS;
    }
}

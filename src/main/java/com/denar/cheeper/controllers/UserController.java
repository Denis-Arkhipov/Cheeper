package com.denar.cheeper.controllers;

import com.denar.cheeper.datalayer.entities.User;
import com.denar.cheeper.datalayer.repositories.UserRepository;
import com.denar.cheeper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsersOnline(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(userService.readAllExceptMe(user.getUsername()), HttpStatus.OK);
    }

//    @PostMapping("/{id}/edit")
//    public ResponseEntity<?> userRegistration(@RequestBody RegistrForm registrForm,
//                                      BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        User newUser = new User(registrForm.getUsername(), registrForm.getPassword());
//        newUser.setRoles(Set.of(Role.USER));
//        newUser.setStatus(Status.OFFLINE);
////        user.setUsername(username);
////        Set<String> roles = Arrays.stream(Role.values())
////                .map(Role::name)
////                .collect(Collectors.toSet());
////
////        user.getRoles().clear();
////
////        for (String key : form.keySet()) {
////            if (roles.contains(key)) {
////                user.getRoles().add(Role.valueOf(key));
////            }
////        }
//
//        userRepository.save(newUser);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}

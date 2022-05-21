package br.com.unialfa.contasapagar.user.service;

import br.com.unialfa.contasapagar.user.business.UserBusiness;
import br.com.unialfa.contasapagar.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserBusiness userBusiness;

    public UserController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @GetMapping(path = "/login")
    public ResponseEntity<?> validateUser(@RequestBody User user){
        return userBusiness.validateLogin(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> listUser() {
        return userBusiness.listUser();
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userBusiness.registerUser(user);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") long id, @RequestBody User user) {
        return userBusiness.editUser(id ,user);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<?> disableUser(@PathVariable("id") long id) {
        return userBusiness.disableUser(id);
    }
}
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

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<User> findById(@PathVariable long id){
        return userBusiness.searchById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> listUser() {
        return userBusiness.listUser();
    }

    @PostMapping(path = "/add")
    public void registerUser(@RequestBody User user) {
        userBusiness.registerUser(user);
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") long id, @RequestBody User user) {
        return userBusiness.editUser(id ,user);
    }

    @PutMapping(value = "/delete/{id}")
    public ResponseEntity<User> disableUser(@PathVariable("id") long id) {
        return userBusiness.disableUser(id);
    }

//    @PutMapping(value = "/delete")
//    public @ResponseBody void disableUser(@RequestBody User user) {
//        userBusiness.disableUser(user);
//    }
}


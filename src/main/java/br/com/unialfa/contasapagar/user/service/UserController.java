package br.com.unialfa.contasapagar.user.service;

import br.com.unialfa.contasapagar.user.business.UserBusiness;
import br.com.unialfa.contasapagar.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserBusiness userBusiness;

    @Autowired
    public UserController(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping(path = "/add")
    public void registerUser(@RequestBody User user) {
        userBusiness.registerUser(user);
    }

    @PutMapping(path = "/edit")
    public void editUser(@RequestBody User user) {
        userBusiness.editUser(user);
    }

    @PutMapping(value = "/delete")
    public @ResponseBody void disableUser(@RequestBody User user) {
        userBusiness.disableUser(user);
    }
}


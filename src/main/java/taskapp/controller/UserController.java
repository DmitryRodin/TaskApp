package taskapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import taskapp.model.snapshots.UserCreationRequest;
import taskapp.model.snapshots.UserSnapshot;
import taskapp.model.snapshots.UserUpdateRequest;
import taskapp.service.SecurityService;
import taskapp.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/findByUsername/{username}", method = GET)
    public UserSnapshot findby(@PathVariable(value = "username") String username) {
        return userService.findByUsername(username);
    }
    @RequestMapping(value = "/getby/{Id}", method = GET)
    public UserSnapshot getbyId(@PathVariable(value = "Id")Long id){
        return userService.getById(id);
    }

    @RequestMapping(value = "/save", method = POST)
    public UserSnapshot save(@RequestBody UserCreationRequest request) {
        return userService.Save(request);
    }

    @RequestMapping(value = "/update", method = POST)
    public void update(@RequestBody UserUpdateRequest request, Long userId) {
        userService.update(request, userId);
    }

    @RequestMapping(value = "/delete", method = DELETE)
    public void delete(@RequestBody Long userId) {
        userService.delete(userId);
    }

    @RequestMapping(value = "/respassword", method = POST)
    public void respasw(@RequestBody Long userId) {
        userService.resetPassword(userId);
    }


    @RequestMapping(value = "/registration", method = POST)
    public String registration(@RequestBody UserCreationRequest creationRequest) {

        UserSnapshot snapshot = userService.Save(creationRequest);

        securityService.autoLogin(snapshot.getUsername(), snapshot.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully");
        }
        return "login";

    }
}



package com.preu.virtualu.controllers;

import com.preu.virtualu.model.JsonResponse;
import com.preu.virtualu.model.User;
import com.preu.virtualu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult){

        JsonResponse result = new JsonResponse();
        User userExist = userService.findUserByEmail(user.getEmail());
        if(userExist != null)
        {
            System.out.println(user);
            result.setMsg("Ya existe un usuario con esa direccion de correo electronico");
            return  ResponseEntity.badRequest().body(result);
        }
        if (bindingResult.hasErrors()){
            result.setMsg(bindingResult.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        } else{
            System.out.println(user);
            userService.saveUser(user);
            result.setMsg("Created Successful");
            result.setResult(user);

            return ResponseEntity.ok(result);

        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestBody User user){
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null)
        {
            userService.removeUserByEmail(user.getEmail());
            return "{ \'response\': \'ok\' }";
        }
        else {
            return "{ \'response\': \'no exist\' }";
        }
    }

}

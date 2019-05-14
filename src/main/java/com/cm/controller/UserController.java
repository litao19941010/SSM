package com.cm.controller;

import com.cm.model.UserModel;
import com.cm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="getAllUsers",method = RequestMethod.GET)
	@ResponseBody
	public List<UserModel> getAllUsers() {
		List<UserModel> userModels = userService.getAllUsers();
		return userModels;
	}

	@RequestMapping(value="getUser",method = RequestMethod.GET)
	@ResponseBody
	public UserModel getUser(String id) {
		UserModel userModel = userService.getUser(id);
		return userModel;
	}
}

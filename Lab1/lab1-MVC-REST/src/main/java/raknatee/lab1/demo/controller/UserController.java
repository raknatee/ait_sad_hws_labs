package raknatee.lab1.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import raknatee.lab1.demo.dao.UserDao;
import raknatee.lab1.demo.dao.UserJPADao;
import raknatee.lab1.demo.model.User;

@Controller
public class UserController {

	@Autowired
	UserDao dao;

	@Autowired
	UserJPADao jpaDao;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String homeFunc() {
		return "home.jsp";
	}

	@RequestMapping(path = "/addUser", method = RequestMethod.GET)
	public String addUserFunc(User userObj) {
		dao.save(userObj);
		return "home.jsp";
	}

	@RequestMapping(path = "getUser", method = RequestMethod.GET)
	public ModelAndView getUserFunc(@RequestParam int uid) {
		ModelAndView mv = new ModelAndView("showUser.jsp");
		mv.addObject("userData", dao.findById(uid).orElse(new User()));

		return mv;
	}

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	@ResponseBody
	public String getUsersFuncRest() {
		return dao.findAll().toString();
	}

	@RequestMapping(path = "/user/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserFuncRest(@PathVariable("uid") int userID) {
		return dao.findById(userID).toString();
	}
	
	
	

	@RequestMapping(path = "/users_json", method = RequestMethod.GET,produces = "application/JSON")
	@ResponseBody
	public List<User> getUsersFuncRestJson() {
		return jpaDao.findAll();
	}
	
	
	@RequestMapping(path = "/user_json", method = RequestMethod.POST,consumes = "application/JSON")
	@ResponseBody
	public String postUser(@RequestBody User user) {
		jpaDao.save(user);
		return user.toString();
	}
	
	
	@RequestMapping(path = "/user_json/{uid}", method = RequestMethod.GET,produces = "application/JSON")
	@ResponseBody
	public Optional<User> getUserFuncRestJson(@PathVariable("uid") int userID) {
		return jpaDao.findById(userID);
	}
	
	@RequestMapping(path = "/user_json/{uid}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUserFuncRestJson(@PathVariable("uid") int userID) {
		User u = jpaDao.getOne(userID);
		jpaDao.delete(u);
		return "delete successfully";
	}
	
	
}

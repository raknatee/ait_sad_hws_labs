package raknatee.lab1.demo.dao;

import org.springframework.data.repository.CrudRepository;

import raknatee.lab1.demo.model.User;

public interface UserDao extends CrudRepository<User, Integer>{

}

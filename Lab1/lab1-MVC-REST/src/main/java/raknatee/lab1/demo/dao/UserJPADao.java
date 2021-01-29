package raknatee.lab1.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import raknatee.lab1.demo.model.User;

public interface UserJPADao extends JpaRepository<User, Integer>{

}

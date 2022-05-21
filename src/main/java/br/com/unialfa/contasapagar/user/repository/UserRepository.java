package br.com.unialfa.contasapagar.user.repository;

import br.com.unialfa.contasapagar.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
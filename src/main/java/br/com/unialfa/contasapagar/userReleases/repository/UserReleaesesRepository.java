package br.com.unialfa.contasapagar.userReleases.repository;

import br.com.unialfa.contasapagar.userReleases.domain.UserReleases;
import org.springframework.data.repository.CrudRepository;

public interface UserReleaesesRepository extends CrudRepository<UserReleases, Long> {
}
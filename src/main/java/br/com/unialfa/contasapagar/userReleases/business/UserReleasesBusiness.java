package br.com.unialfa.contasapagar.userReleases.business;

import br.com.unialfa.contasapagar.userReleases.domain.UserReleases;
import br.com.unialfa.contasapagar.userReleases.repository.UserReleaesesRepository;
import org.springframework.stereotype.Service;

@Service
public class UserReleasesBusiness {

    private final UserReleaesesRepository userReleaesesRepository;

    public UserReleasesBusiness(UserReleaesesRepository userReleaesesRepository) {
        this.userReleaesesRepository = userReleaesesRepository;
    }

    public void registerUserReleases(UserReleases userReleases) {
        try {
            userReleaesesRepository.save(userReleases);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
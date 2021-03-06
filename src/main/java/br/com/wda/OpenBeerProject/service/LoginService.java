package br.com.wda.OpenBeerProject.Service;

import br.com.wda.OpenBeerProject.Entity.Login;
import br.com.wda.OpenBeerProject.Repository.LoginRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Wesley Santos_2
 */

@Service
public class LoginService  implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Login> usuario = loginRepository.findByEmail(email);
        
        if(usuario.isPresent() == false){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return usuario.get();
    }
    
}

package br.com.wda.OpenBeerProject.Repository;

import br.com.wda.OpenBeerProject.Entity.Cupom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CupomRepository extends JpaRepository<Cupom, Integer>{
    
    public Optional<Cupom> findByCupom(String cupom);
}

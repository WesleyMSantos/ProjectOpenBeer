/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wda.OpenBeerProject.Entity;

import br.com.wda.OpenBeerProject.Security.SecurityConfig;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Darlan Silva
 * @author Wesley Moura
 * @author Alison Souza
 */

@Entity
@Table(name = "TS_LOGIN")
public class Login implements UserDetails{
    
    //private List<Permissao> papeis;
    
    @Id
    @Column(name = "PK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "CAMPO EMAIL OBRIGATÓRIO")
    @Column(name = "EMAIL")
    private String email;
    
    @NotBlank(message = "CAMPO SENHA OBRIGATÓRIO")
    @Column(name = "SENHA")
    private String hashSenha;
    
    @JoinTable(name = "TS_PERMISSAOACESSO")
    @Column(name = "FK_PERMISSAOACESSO")
    private int permissaoAcesso;        
    
    @Column(name = "TG_INATIVO")
    private int inativo;
    
    @Column(name = "DH_INCLUSAO", nullable = false, insertable = true, updatable = false)
    private LocalDateTime dhInclusao;

    @Column(name = "DH_ALTERACAO", nullable = true, insertable = true, updatable = true)
    private LocalDateTime dhAlteracao;

    public Login() {
    }

    public Login(Long id, String email, String hashSenha, int permissaoAcesso, int inativo, LocalDateTime dhInclusao, LocalDateTime dhAlteracao) {
        this.id = id;
        this.email = email;
        this.hashSenha = hashSenha;
        this.permissaoAcesso = permissaoAcesso;
        this.inativo = inativo;
        this.dhInclusao = dhInclusao;
        this.dhAlteracao = dhAlteracao;
    }
    
//    public final void setSenha(String senhaAberta){
//        this.hashSenha = SecurityConfig.bcryptPasswordEncoder().encode(senhaAberta);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String senhaAberta) {
        this.hashSenha = SecurityConfig.bcryptPasswordEncoder().encode(senhaAberta);
    }

    public int getPermissaoAcesso() {
        return permissaoAcesso;
    }

    public void setPermissaoAcesso(int permissaoAcesso) {
        this.permissaoAcesso = permissaoAcesso;
    }

//    public List<Permissao> getPapeis() {
//        return papeis;
//    }
//
//    public void setPapeis(List<Permissao> papeis) {
//        this.papeis = papeis;
//    }        

    public int getInativo() {
        return inativo;
    }

    public void setInativo(int inativo) {
        this.inativo = inativo;
    }

    public LocalDateTime getDhInclusao() {
        return dhInclusao;
    }

    public void setDhInclusao(LocalDateTime dhInclusao) {
        this.dhInclusao = dhInclusao;
    }

    public LocalDateTime getDhAlteracao() {
        return dhAlteracao;
    }

    public void setDhAlteracao(LocalDateTime dhAlteracao) {
        this.dhAlteracao = dhAlteracao;
    }  
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public List<Permissao> getAuthorities() {
//        return getPapeis();
//    }

    @Override
    public String getPassword() {
        return getHashSenha();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}

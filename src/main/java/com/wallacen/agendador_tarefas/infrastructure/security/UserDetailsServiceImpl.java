package com.wallacen.agendador_tarefas.infrastructure.security;

import com.wallacen.agendador_tarefas.business.dto.UsuarioDto;
import com.wallacen.agendador_tarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {


    private final UsuarioClient usuarioClient;

    public UserDetailsServiceImpl(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }


    public UserDetails carregaroDadosDosUsuarios(String email, String token){

        UsuarioDto usuarioDto = usuarioClient.buscaUsuarioPorEmail(email,"Bearer " + token);
        return User
                .withUsername(usuarioDto.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDto.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}


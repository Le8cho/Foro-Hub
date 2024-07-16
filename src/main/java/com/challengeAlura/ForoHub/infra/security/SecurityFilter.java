package com.challengeAlura.ForoHub.infra.security;

import com.challengeAlura.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //METODO PARA LA CADENA DE FILTROS OH YES
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //primero verificar el token oh yes
        var bearerToken = request.getHeader("Authorization");

        //Verificar el token solo si ha llegado alguno
        if(bearerToken != null){
            bearerToken = bearerToken.replace("Bearer ",""); //Extraemos el prefijo bearer
            //Obtenemos el nombre del usuario
            var username = tokenService.verificarJwt(bearerToken);
            //Buscamos al usuario en la base de datos
            var usuario = usuarioRepository.findByNombre(username);
            //Forzamos autenticacion
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            //Fuerzo inicio de sesion
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }
}

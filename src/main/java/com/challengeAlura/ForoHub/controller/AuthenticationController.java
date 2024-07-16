package com.challengeAlura.ForoHub.controller;

import com.challengeAlura.ForoHub.domain.usuario.DatosAutenticacionUsuario;
import com.challengeAlura.ForoHub.domain.usuario.Usuario;
import com.challengeAlura.ForoHub.infra.security.DatoJWT;
import com.challengeAlura.ForoHub.infra.security.JWTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    //autenticacion: eres quien dices ser?
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTokenService jwtTokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos){
        var authentication = new UsernamePasswordAuthenticationToken(datos.nombre(),datos.contrasena());
        //Obtenemos el usuario autenticado del authentication manager
        var usuarioAutenticado = (Usuario) authenticationManager.authenticate(authentication).getPrincipal();
        var jwtoken =  jwtTokenService.generarJWT(usuarioAutenticado);
        return ResponseEntity.ok().body(new DatoJWT("Usuario autenticado",jwtoken));
    }

}

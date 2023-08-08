package com.company.jwt.service;

import com.company.jwt.model.dto.request.AuthenticationRequest;
import com.company.jwt.model.dto.request.RegisterRequest;
import com.company.jwt.model.dto.response.AuthenticationResponse;
import com.company.jwt.model.dto.response.GeneralResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAuthenticationService {
    GeneralResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}




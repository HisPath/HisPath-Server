package com.server.hispath.auth.application;

import java.util.Objects;
import java.util.Optional;

import com.server.hispath.auth.application.dto.LoginRequestDto;
import com.server.hispath.auth.application.dto.LoginResponseDto;
import com.server.hispath.auth.domain.LoginManager;
import com.server.hispath.auth.domain.LoginStudent;
import com.server.hispath.auth.domain.Member;
import com.server.hispath.auth.domain.OauthUserInfo;
import com.server.hispath.auth.infrastructure.JwtProvider;
import com.server.hispath.auth.infrastructure.OauthHandler;
import com.server.hispath.exception.manager.ManagerNoAuthorizationException;
import com.server.hispath.exception.oauth.InvalidTokenException;
import com.server.hispath.exception.oauth.NotHandongEmailException;
import com.server.hispath.manager.application.ManagerService;
import com.server.hispath.manager.domain.Manager;
import com.server.hispath.manager.domain.repository.ManagerRepository;
import com.server.hispath.student.application.StudentService;
import com.server.hispath.student.domain.Student;
import com.server.hispath.student.domain.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final OauthHandler oauthHandler;
    private final JwtProvider jwtProvider;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final ManagerRepository managerRepository;
    private final ManagerService managerService;

    public void validateEamil(String email) {
        String domain = email.split("@")[1];
        if(!Objects.equals(domain, "handong.ac.kr")){
            throw new NotHandongEmailException();
        };
    }

    public OauthUserInfo getUserInfo(LoginRequestDto loginRequestDto) {
        String oauthProvider = loginRequestDto.getOauthProvider();
        return oauthHandler.getUserInfoFromCode(oauthProvider, loginRequestDto.getCode());
    }

    @Transactional(readOnly = true)
    public LoginResponseDto studentLogin(LoginRequestDto loginRequestDto) {
        OauthUserInfo userInfo = getUserInfo(loginRequestDto);
        String email = userInfo.getEmail();
        validateEamil(email);
        Optional<Student> student = studentRepository.findByEmail(email);
        return student.map(value -> new LoginResponseDto(false,
                              jwtProvider.createToken(String.valueOf(value.getId()), Member.STUDENT)))
                      .orElseGet(() -> new LoginResponseDto(true, null));
    }

    @Transactional(readOnly = true)
    public LoginResponseDto managerLogin(LoginRequestDto loginRequestDto) {
        OauthUserInfo userInfo = getUserInfo(loginRequestDto);
        String email = userInfo.getEmail();
        Optional<Manager> manager = managerRepository.findByEmail(email);
        return manager.map(value -> new LoginResponseDto(value.isApproved(),
                              jwtProvider.createToken(String.valueOf(value.getId()), Member.MANAGER)))
                      .orElseGet(() -> new LoginResponseDto(true, null));

    }

    @Transactional(readOnly = true)
    public LoginStudent findStudentByToken(String token) {
        if (!jwtProvider.isValidToken(token, Member.STUDENT)) {
            throw new InvalidTokenException();
        }

        String payLoad = jwtProvider.getPayLoad(token, Member.STUDENT);
        Long id = Long.parseLong(payLoad);
        Student student = studentService.findById(id);
        student.updateLogin();
        return new LoginStudent(student.getId(), student.getStudentNum());
    }

    @Transactional(readOnly = true)
    public LoginManager findManagerByToken(String token) {
        if (!jwtProvider.isValidToken(token, Member.MANAGER)) {
            throw new InvalidTokenException();
        }
        String payLoad = jwtProvider.getPayLoad(token, Member.MANAGER);
        Long id = Long.parseLong(payLoad);
        Manager manager = managerService.findById(id);
        return new LoginManager(manager.getId());
    }



    @Transactional(readOnly = true)
    public void checkSuperManagerByToken(String token) {
        Manager manager = findSuperManagerByToken(token);
        if(!manager.isSuperManager()) throw new ManagerNoAuthorizationException();
    }

    @Transactional(readOnly = true)
    public LoginManager getSuperManagerId(String token){
        Manager manager = findSuperManagerByToken(token);
        return new LoginManager(manager.getId());
    }

    @Transactional(readOnly = true)
    public Manager findSuperManagerByToken(String token){
        if (!jwtProvider.isValidToken(token, Member.SUPER_MANAGER)) {
            throw new InvalidTokenException();
        }
        String payLoad = jwtProvider.getPayLoad(token, Member.SUPER_MANAGER);
        Long id = Long.parseLong(payLoad);
        Manager manager = managerService.findById(id);
        if(!manager.isSuperManager()) throw new ManagerNoAuthorizationException();
        return manager;
    }
}

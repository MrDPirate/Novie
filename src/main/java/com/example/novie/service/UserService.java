package com.example.novie.service;

import com.example.novie.exception.InformationExistException;
import com.example.novie.mailing.AccountPasswordResetEmailContext;
import com.example.novie.mailing.AccountVerificationEmailContext;
import com.example.novie.mailing.EmailService;
import com.example.novie.model.SecureToken;
import com.example.novie.model.User;
import com.example.novie.model.request.LoginRequest;
import com.example.novie.model.response.LoginResponse;
import com.example.novie.repository.UserRepository;
import com.example.novie.security.JWTUtils;
import com.example.novie.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class UserService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private MyUserDetails myUserDetails;

    @Autowired
    EmailService emailService;

    @Autowired
    private SecureTokenService secureTokenService;


    private UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,JWTUtils jwtUtils,
                        @Lazy AuthenticationManager authenticationManager,
                        @Lazy MyUserDetails myUserDetails){
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
        this.jwtUtils =jwtUtils;
        this.authenticationManager=authenticationManager;
        this.myUserDetails = myUserDetails;

    }

    public User createUser(User userObject){
        System.out.println("service calling create user");
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())){
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));

            User result = userRepository.save(userObject);
            sendConfirmationEmail(userObject);
            return result;
        }else {
            throw new InformationExistException("User already exist");
        }
    }


    public void sendConfirmationEmail(User user) {
        SecureToken secureToken = secureTokenService.createToken();
        secureToken.setUser(user);
        secureTokenService.saveSecureToken(secureToken);
        AccountVerificationEmailContext context = new AccountVerificationEmailContext();
        context.init(user);
        context.setToken(secureToken.getToken());
        context.buildVerificationUrl("http://localhost:8080/", secureToken.getToken());

        System.out.println("sending email to " + user.getEmailAddress());
        emailService.sendMail(context);
    }
    public User findUserByEmailAddress(String email){
        return userRepository.findUserByEmailAddress(email);
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),loginRequest.getPassword()
        );
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            myUserDetails =(MyUserDetails) authentication.getPrincipal();
            final String JWT = jwtUtils.generateJwtToken(myUserDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        } catch (Exception e) {
            return ResponseEntity.ok(new LoginResponse("Error : Username or pass is incorrect"));
        }
    }


    public void changePassword(String oldPassword, String newPassword) {

        User user =myUserDetails.getUser();
        try {
            if(passwordEncoder.matches(oldPassword, user.getPassword())){

                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

      public void validate(String token) {
        SecureToken secureToken = secureTokenService.findByToken(token);
        User user = secureToken.getUser();
        user.setAccountVerified(true);
        userRepository.save(user);
    }

    public void resetPassword(String emailAddress) {
        SecureToken secureToken = secureTokenService.createToken();
        User user = userRepository.findUserByEmailAddress(emailAddress);
        System.out.println("service found user ====> " + user.getUserName());
        secureToken.setUser(user);
        secureTokenService.saveSecureToken(secureToken);
        AccountPasswordResetEmailContext context = new AccountPasswordResetEmailContext();
        context.init(user);
        context.setToken(secureToken.getToken());
        context.buildResetUrl("http://localhost:8080/", secureToken.getToken());

        System.out.println("sending email to " + user.getEmailAddress());
        emailService.sendMail(context);
    }

    public void resetPasswordActivator(String token, User userObj) {
        SecureToken secureToken = secureTokenService.findByToken(token);
        User user = secureToken.getUser();
        user.setPassword(passwordEncoder.encode(userObj.getPassword()));
        userRepository.save(user);
    }
}

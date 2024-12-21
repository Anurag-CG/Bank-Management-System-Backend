package com.example.loginservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginservice.entities.Admin;
import com.example.loginservice.entities.BankerLogin;
import com.example.loginservice.entities.Login;
import com.example.loginservice.repository.AdminRepository;
import com.example.loginservice.repository.BankerRepository;
import com.example.loginservice.repository.LoginRepository;

import java.util.Collections;

@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private BankerRepository bankerRepo;
	@Autowired
	private LoginRepository loginRepo;
	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private PasswordEncoder encoder;
	
	public UserDetails authenticateAdmin(String adminId, String password) {
        Admin admin = adminRepo.findById(adminId).orElse(null);
        if (admin != null && password.equals(admin.getPassword())) {
            return new User(admin.getAdminId(), admin.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        return null;
    }
	
	public UserDetails authenticateBanker(String bankerId, String password) {
        BankerLogin banker = bankerRepo.findById(bankerId).orElse(null);
        if (banker != null && encoder.matches(password, banker.getPassword())) {
            return new User(banker.getBankerId().toString(), banker.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_BANKER")));
        }
        return null;
    }
	
	public UserDetails authenticateUser(String userId, String password) {
        Login login = loginRepo.findById(userId).orElse(null);
        if (login != null && encoder.matches(password, login.getPassword())) {
            return new User(login.getUserId(), login.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }
        return null;
    }

    // Override loadUserByUsername to implement UserDetailsService interface
    @Override
    public UserDetails loadUserByUsername(String username) {
        // You could implement more complex logic if needed, but for now, this is sufficient.
        return null;
    }
}

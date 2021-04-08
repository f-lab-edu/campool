package com.campool.service;

import com.campool.enumeration.Role;
import com.campool.model.LoginRequest;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessionAuthService implements AuthService {

    public static final String ID_KEY = "id";
    public static final String ROLE_KEY = "role";

    @NonNull
    private final UserService userService;

    @NonNull
    private final AdminService adminService;

    @NonNull
    private final HttpSession session;

    @Override
    public void authenticate(LoginRequest loginRequest) {
        String id = loginRequest.getId();
        String password = loginRequest.getPassword();
        Role role = loginRequest.getRole();
        if (isAdmin(role)) {
            adminService.getByIdAndPw(id, password);
        } else {
            userService.getByIdAndPw(id, password);
        }
        session.setAttribute(ID_KEY, id);
        session.setAttribute(ROLE_KEY, role);
    }

    @Override
    public void deauthenticate() {
        session.invalidate();
    }

    @Override
    public boolean isValidAuthentication() {
        return session.getAttribute(ID_KEY) != null;
    }

    @Override
    public String getAuthenticatedUserId() {
        return (String) session.getAttribute(ID_KEY);
    }


    private boolean isAdmin(Role role) {
        return role.equals(Role.ADMIN);
    }

}

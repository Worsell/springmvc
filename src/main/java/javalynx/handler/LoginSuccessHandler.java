package javalynx.handler;

import javalynx.model.Role;
import javalynx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private RoleService roleService;

    public LoginSuccessHandler(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println(authentication.getAuthorities());
        if (authentication.getAuthorities().contains(roleService.getAdminRole())) {
            httpServletResponse.sendRedirect("/admin");
        } else if (authentication.getAuthorities().contains(roleService.getUserRole())) {
            httpServletResponse.sendRedirect("/user");
        } else {
            httpServletResponse.sendRedirect("/login?nothingyet");
        }
    }
}
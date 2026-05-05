package spring.security.inc.controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController
{
	@GetMapping("/hello")
    public String hello()
    {
        return "Hello User, JWT Authentication Working 🚀";
    }

    @GetMapping("/profile")
    public String profile(Authentication authentication)
    {
        return "Welcome : " + authentication.getName();
    }

}

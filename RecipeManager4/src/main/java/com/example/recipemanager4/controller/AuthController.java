package com.example.recipemanager4.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recipemanager4.entity.User;
import com.example.recipemanager4.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session, Model model) {
        User found = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (found != null) {
            session.setAttribute("loginUser", found);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
 // ✅ アカウント登録画面の表示
    @GetMapping("/registerAccount")
    public String showRegisterAccountForm(Model model) {
        model.addAttribute("user", new User());
        return "registerAccount";
    }

    // ✅ アカウント登録処理
    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            model.addAttribute("error", "このメールアドレスは既に使用されています");
            return "registerAccount";
        }

        userRepository.save(user);  // 必要に応じて password をハッシュ化
        return "redirect:/registerSuccess"; // ★ 登録完了ページへ遷移
    }

    // ✅ 登録完了画面の表示
    @GetMapping("/registerSuccess")
    public String showRegisterSuccessPage() {
        return "registerSuccess";
    }
}

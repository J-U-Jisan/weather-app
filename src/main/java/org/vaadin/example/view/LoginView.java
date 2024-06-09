package org.vaadin.example.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import org.vaadin.example.service.UserService;

@Route("login")
public class LoginView extends VerticalLayout {

    @Inject
    private UserService userService;

    public LoginView() {
        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login");

        loginButton.addClickListener(event -> {
            String email = emailField.getValue();
            String password = passwordField.getValue();

            userService.login(email, password).ifPresentOrElse(user -> {
                // Navigate to home
                getUI().ifPresent(ui -> ui.navigate("home"));
            }, () -> {
                add(new NativeLabel("Invalid email or password"));
            });
        });

        add(emailField, passwordField, loginButton);
    }
}

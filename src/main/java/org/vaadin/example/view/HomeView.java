package org.vaadin.example.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import org.vaadin.example.MainLayout;
import org.vaadin.example.service.SecurityService;

@Route(value = "home", layout = MainLayout.class)
public class HomeView extends VerticalLayout implements SecuredView{

    @Inject
    SecurityService securityService;
    public HomeView() {

        Button logoutButton = new Button("Logout");

        logoutButton.addClickListener(event -> {
            securityService.logout();
            // Navigate to login page
            getUI().ifPresent(ui -> ui.navigate("login"));
        });
        add(logoutButton, new NativeLabel("Hello from home page"));
    }
}

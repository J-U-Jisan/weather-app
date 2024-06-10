package org.vaadin.example;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.RouterLayout;
import jakarta.inject.Inject;
import org.vaadin.example.service.SecurityService;
import org.vaadin.example.view.GuestView;
import org.vaadin.example.view.SecuredView;

public class GuestLayout extends VerticalLayout implements RouterLayout, BeforeEnterObserver {
    @Inject
    private SecurityService securityService;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (GuestView.class.isAssignableFrom(event.getNavigationTarget())) {
            if (securityService.isAuthenticated()) {
                event.forwardTo("");
            }
        }
    }
}

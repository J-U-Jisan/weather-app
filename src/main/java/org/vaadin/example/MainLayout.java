package org.vaadin.example;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import jakarta.inject.Inject;
import org.vaadin.example.service.SecurityService;
import org.vaadin.example.view.SecuredView;

import java.lang.annotation.Annotation;

public class MainLayout extends VerticalLayout implements BeforeEnterObserver {

    @Inject
    private SecurityService securityService;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getNavigationTarget().isAnnotationPresent((Class<? extends Annotation>) SecuredView.class)) {
            if (!securityService.isAuthenticated()) {
                event.forwardTo("login");
            }
        }
    }
}

package org.vaadin.example.view;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import jakarta.annotation.PostConstruct;
import org.vaadin.example.SecuredLayout;

@Route(value="location/:locationId/forecast", layout = SecuredLayout.class)
public class DailyForecastView extends VerticalLayout implements SecuredView, BeforeEnterObserver {

    private Integer locationId;

    public DailyForecastView() {

    }
    public void init() {
        H3 pageTitle = new H3("Daily Forecast");
        add(pageTitle);
    }


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String locationIdParameter = event.getRouteParameters().get("locationId").orElse(null);
        if (locationIdParameter != null) {
            try {
                this.locationId = Integer.parseInt(locationIdParameter);
            } catch (NumberFormatException e) {
                // Show an error notification if the locationId is not a valid integer
                Notification.show("Invalid location ID: " + locationIdParameter, 3000, Notification.Position.BOTTOM_CENTER);
                this.locationId = null;
            }
        }
        // Initialize the view
        init();
    }
}

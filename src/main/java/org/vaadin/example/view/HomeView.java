package org.vaadin.example.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.vaadin.example.MainLayout;
import org.vaadin.example.entity.Location;
import org.vaadin.example.service.LocationService;
import org.vaadin.example.service.SecurityService;
import org.vaadin.tatu.BeanTable;

import java.util.List;

@Route(value = "home", layout = MainLayout.class)
public class HomeView extends VerticalLayout implements SecuredView{

    @Inject
    SecurityService securityService;
    @Inject
    LocationService locationService;

    private BeanTable<Location> beanTable;
    private TextField locationField = new TextField("Location");
    private Button searchButton = new Button("Search");
    private List<Location> locations;


    @PostConstruct
    public void HomeView() {

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidthFull();

        Button logoutButton = new Button("Logout");

        logoutButton.addClickListener(event -> {
            securityService.logout();
            // Navigate to login page
            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        H1 title = new H1("Home");
        topLayout.add( title, logoutButton);
        topLayout.expand(title);
        topLayout.setFlexGrow(1, title);
        topLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);

        HorizontalLayout searchFilterLayout = new HorizontalLayout();
        searchButton.addClickListener(e -> searchLocations());
        searchButton.getStyle().setMarginTop("auto"
        );


        searchFilterLayout.add(locationField, searchButton);

        beanTable = new BeanTable<>(Location.class, false, 10);
        beanTable.addColumn("Location", Location::getName);
        beanTable.addColumn("Country", Location::getCountry);
        beanTable.addColumn("Latitude", Location::getLatitude);
        beanTable.addColumn("Longitude", Location::getLongitude);
        beanTable.setWidthFull();
        searchLocations("Dhaka");

        add(topLayout, searchFilterLayout, beanTable);
    }

    private void searchLocations() {
        String locationName = locationField.getValue();
        searchLocations(locationName);
    }

    private void searchLocations(String locationName) {
        locations = locationService.getLocations(locationName);
        updateGrid();
    }

    private void updateGrid() {
        beanTable.setItems(locations);
    }
}

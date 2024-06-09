package org.vaadin.example.view;

import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("home")
public class HomeView extends VerticalLayout implements SecuredView{

    public HomeView() {
        add(new NativeLabel("Hello from home page"));
    }
}

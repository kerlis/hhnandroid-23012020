package me.doapps.appdhn.models;

/**
 * Created by William_ST on 10/08/16.
 */
public class FrequentQuestion {
    private  String menu, submenu;

    public FrequentQuestion(String menu, String submenu) {
        this.menu = menu;
        this.submenu = submenu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }
}

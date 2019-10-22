package me.doapps.appdhn.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by William_ST on 05/10/15.
 */
@DatabaseTable(tableName = "Charts")
public class Charts {

    @DatabaseField(generatedId = true)
    private int idCharts;

    @DatabaseField(canBeNull = false)
    private String menu;

    @DatabaseField(canBeNull = false)
    private int icon;

    @DatabaseField(canBeNull = false)
    private String submenu;

    @DatabaseField(canBeNull = false)
    private String url;

    public Charts() {
    }

    public Charts(String menu, int icon, String submenu, String url) {
        this.menu = menu;
        this.icon = icon;
        this.submenu = submenu;
        this.url = url;
    }

    public int getIdCharts() {
        return idCharts;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package pl.robertprogramista.noticeboard.model;

import javax.persistence.*;

@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String theme;
    private String description;
    private String positionTop;
    private String positionLeft;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPositionTop() {
        return positionTop;
    }

    public void setPositionTop(String positionTop) {
        this.positionTop = positionTop;
    }

    public String getPositionLeft() {
        return positionLeft;
    }

    public void setPositionLeft(String positionLeft) {
        this.positionLeft = positionLeft;
    }

    public void updateNotice(final Notice source) {
        description = source.description;
        id = source.id;
        theme = source.theme;
        positionLeft = source.positionLeft;
        positionTop = source.positionTop;
    }
}

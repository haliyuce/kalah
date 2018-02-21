package com.backbase.homework.kalah.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class Player {

    @Range
    private int id = -1;

    @NotNull
    private String name;

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}

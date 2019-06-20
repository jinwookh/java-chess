package chess.domain;

import java.util.List;

abstract public class Unit {
    private Team team;

    public Unit(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public abstract boolean validateDirection(Vector vector);

    public boolean isEqualTeam(Unit unit) {
        return this.team.equals(unit.team);
    };
}

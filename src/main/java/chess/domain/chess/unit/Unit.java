package chess.domain.chess.unit;

import chess.domain.chess.game.Team;
import chess.domain.geometric.Vector;

abstract public class Unit {
    private Team team;
    private String name;

    public Unit(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public abstract boolean validateDirection(Vector vector);

    public boolean isEqualTeam(Unit unit) {
        return this.team.equals(unit.team);
    }

    public String getName() {
        return this.name;
    }
}

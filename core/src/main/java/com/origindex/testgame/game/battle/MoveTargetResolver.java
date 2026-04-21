package com.origindex.testgame.game.battle;

import com.badlogic.gdx.graphics.Texture;
import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.view.battle.sprites.PokemonSpriteManager;

public class MoveTargetResolver {
    public static ActivePokemon resolveMoveTarget(ActivePokemon attacker, ActivePokemon defender, String target) {
        switch (target) {
            case "selected-pokemon": return defender;
            case "all-opponents": return defender;
            default: return defender;
        }
    }
}

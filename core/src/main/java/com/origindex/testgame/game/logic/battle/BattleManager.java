package com.origindex.testgame.game.logic.battle;

import com.origindex.testgame.game.entity.ActiveMove;
import com.origindex.testgame.game.entity.ActivePokemon;
import com.origindex.testgame.game.entity.PokemonStatValue;
import com.origindex.testgame.game.model.Stat;

import java.util.*;

public class BattleManager {
    private Battle battle;
    private List<PokemonStatValue> tmpPlayerStats;
    private List<PokemonStatValue> tmpEnemyStats;
    private Map<Stat, Integer> playerStatsMapModifier;
    private Map<Stat, Integer> enemyStatsMapModifier;

    /**
     * Method that handles the battle turn
     */
    public void handleTurn(ActivePokemon attackerPokemon, ActiveMove attackerMove, ActivePokemon enemyPokemon, ActiveMove enemyMove, List<String> messages){
        //Needed attributes from the attacker pokemon
        if (!MoveResolver.resolvePlayerMove(attackerPokemon, attackerMove, messages)){
            messages.add(MoveResolver.showPPMessage(attackerMove));
            return;
        }
        messages.add(MoveResolver.showChosenMove(attackerPokemon, attackerMove));
        List<PokemonStatValue> playerStats = getTmpPlayerStats();
        Map<Stat, Integer> playerStatsMap = getPlayerStatsMapModifier();
        int playerLevel = attackerPokemon.getLevel();
        int playerSpeed = attackerPokemon.getSpeedStat();
        int playerAccuracy = attackerPokemon.getAccuracy();
        int playerEvasion = attackerPokemon.getEvasion();

        //Needed attributes from the defender pokemon
        List<PokemonStatValue> enemyStats = getTmpEnemyStats();
        Map<Stat, Integer> enemyStatsMap = getEnemyStatsMapModifier();
        int enemyLevel = enemyPokemon.getLevel();
        int enemySpeed = enemyPokemon.getSpeedStat();
        int enemyAccuracy = enemyPokemon.getAccuracy();
        int enemyEvasion = enemyPokemon.getEvasion();

        //Maps that contains the multipliers from accuracy and evasion in function from the stats of the pokemon
        Map<Integer, Double> accuracyMap = getAccuracyMap();
        Map<Integer, Double> evasionMap = getEvasionMap();

        boolean isHitting = AccuracyResolver.resolveAccuracy(attackerMove, accuracyMap.get(playerAccuracy), evasionMap.get(enemyEvasion));
        double damageModifier = DamageModifierCalculator.calculateDamageModifier(attackerPokemon, enemyPokemon, attackerMove.getMove(), messages);
        int damage = handleDamage(attackerPokemon, playerStatsMap, playerStats, enemyPokemon, enemyStatsMap, enemyStats,attackerMove, damageModifier, messages);

        AttackResolver.resolvePokemonAttack(attackerPokemon, attackerMove, enemyPokemon, damage, isHitting, messages);

        battle.nextTurn(); //Pass to the next turn
    }


    /**
     * Method that handles the damage
     * @param pokemon attacker pokemon
     * @param pkmnStatMap attacker pokemon map stats
     * @param pkmnStats attacker pokemon list stats
     * @param enemy defender pokemon
     * @param enemyStatMap defender pokemon map stats
     * @param enemyStats defender pokemon list stats
     * @param move used move
     * @param modifier damage modifier
     * @return damage dealt
     */
    private int handleDamage(ActivePokemon pokemon, Map<Stat, Integer> pkmnStatMap, List<PokemonStatValue> pkmnStats, ActivePokemon enemy, Map<Stat, Integer> enemyStatMap,List<PokemonStatValue> enemyStats, ActiveMove move, double modifier, List<String> messages) {
        if (move.getMove().isStatusMove()){
            StatusMoveHandler.handleStatusMove(pokemon, enemyStatMap, enemyStats, move, enemy, messages);
            return 0;
        }

        return DamageCalculator.calculateDamage(pokemon, move, enemy, modifier);
    }

    private Map<Stat, Integer> initStatsMapModifier(List<PokemonStatValue> stats){
        Map<Stat, Integer> statsMapModifier = new HashMap<>();

        for (PokemonStatValue statValue: stats){
            statsMapModifier.put(statValue.getStat(), 0);
        }

        return statsMapModifier;
    }

    private Map<Integer, Double> getAccuracyMap(){
        Map<Integer, Double> accuracyMap = new HashMap<>();

        accuracyMap.put(6, 3.0);
        accuracyMap.put(5, 2.67);
        accuracyMap.put(4, 2.33);
        accuracyMap.put(3, 2.0);
        accuracyMap.put(2, 1.67);
        accuracyMap.put(1, 1.33);
        accuracyMap.put(0, 1.0);
        accuracyMap.put(-1, 0.75);
        accuracyMap.put(-2, 0.6);
        accuracyMap.put(-3, 0.5);
        accuracyMap.put(-4, 0.43);
        accuracyMap.put(-5, 0.38);
        accuracyMap.put(-6, 0.33);

        return accuracyMap;
    }

    private Map<Integer, Double> getEvasionMap(){
        Map<Integer, Double> evasionMap = new HashMap<>();

        evasionMap.put(6, 0.33);
        evasionMap.put(5, 0.38);
        evasionMap.put(4, 0.43);
        evasionMap.put(3, 0.5);
        evasionMap.put(2, 0.6);
        evasionMap.put(1, 0.75);
        evasionMap.put(0, 1.0);
        evasionMap.put(-1, 1.33);
        evasionMap.put(-2, 1.67);
        evasionMap.put(-3, 2.0);
        evasionMap.put(-4, 2.33);
        evasionMap.put(-5, 2.67);
        evasionMap.put(-6, 3.0);

        return evasionMap;
    }

    public BattleManager(Battle battle) {
        this.battle = battle;
        this.tmpPlayerStats = battle.getPokemonPlayer().getStats();
        this.tmpEnemyStats = battle.getPokemonEnemy().getStats();
        this.playerStatsMapModifier = initStatsMapModifier(tmpPlayerStats);
        this.enemyStatsMapModifier = initStatsMapModifier(tmpEnemyStats);
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public List<PokemonStatValue> getTmpPlayerStats() {
        return tmpPlayerStats;
    }

    public void setTmpPlayerStats(List<PokemonStatValue> tmpPlayerStats) {
        this.tmpPlayerStats = tmpPlayerStats;
    }

    public List<PokemonStatValue> getTmpEnemyStats() {
        return tmpEnemyStats;
    }

    public void setTmpEnemyStats(List<PokemonStatValue> tmpEnemyStats) {
        this.tmpEnemyStats = tmpEnemyStats;
    }

    public Map<Stat, Integer> getPlayerStatsMapModifier() {
        return playerStatsMapModifier;
    }

    public void setPlayerStatsMapModifier(Map<Stat, Integer> playerStatsMapModifier) {
        this.playerStatsMapModifier = playerStatsMapModifier;
    }

    public Map<Stat, Integer> getEnemyStatsMapModifier() {
        return enemyStatsMapModifier;
    }

    public void setEnemyStatsMapModifier(Map<Stat, Integer> enemyStatsMapModifier) {
        this.enemyStatsMapModifier = enemyStatsMapModifier;
    }
}

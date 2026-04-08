package com.origindex.testgame.game.entity;

import com.origindex.testgame.game.model.Pokemon;
import com.origindex.testgame.game.model.PokemonStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivePokemon {
    public static final String HP_STAT = "hp";
    public static final String ATTACK_STAT = "attack";
    public static final String DEFENSE_STAT = "defense";
    public static final String SP_ATTACK_STAT = "special-attack";
    public static final String SP_DEFENSE_STAT = "special-defense";
    public static final String SPEED_STAT = "speed";
    public static final String ACCURACY_STAT = "accuracy";
    public static final String EVASION_STAT = "evasion";
    private Pokemon specie;
    private String nickname;
    private int level;
    private int currentHP;
    private int maxHP;
    private int currentXp;
    private int nextLevelXp;
    private List<PokemonStatValue> stats;
    private List<ActiveMove> learnedMoves;
    private Map<String, Integer> statStages;
    private boolean isFainted;
    private boolean isPlayer;

    public int getModifiedAttack(){
        return getModifiedStat(ATTACK_STAT);
    }

    public int getModifiedDefense(){
        return getModifiedStat(DEFENSE_STAT);
    }

    public int getModifiedSpecialAttack(){
        return getModifiedStat(SP_ATTACK_STAT);
    }

    public int getModifiedSpecialDefense(){
        return getModifiedStat(SP_DEFENSE_STAT);
    }

    public int getModifiedSpeed(){
        return getModifiedStat(SPEED_STAT);
    }

    public int getAccuracyStage(){
        return statStages.get(ACCURACY_STAT);
    }

    public int getEvasionStage(){
        return statStages.get(EVASION_STAT);
    }

    private int getModifiedStat(String identifier){
        int finalStat = getFinalStatByIdentifier(identifier);
        int stage = statStages.getOrDefault(identifier, 0);

        double modifier = getStatStageModifier(stage);

        return (int) (finalStat * modifier);
    }

    public double getAccuracyEvasionStageModifier(int stage){
        if (stage >= 0) {
            return (3.0 + stage) / 3.0;
        } else {
            return 3.0 / (3.0 - stage);
        }
    }

    private double getStatStageModifier(int stage) {
        if (stage >= 0){
            return (2.0 + stage) / 2.0;
        }else {
            return 2.0 / (2.0 - stage);
        }
    }

    public void initStatsMapModifier(){
        statStages = new HashMap<>();
        for (PokemonStatValue statValue: stats){
            String statIdentifier = statValue.getStat().getIdentifier();
            statStages.put(statIdentifier, 0);
        }

        statStages.put(ACCURACY_STAT, 0);
        statStages.put(EVASION_STAT, 0);
    }

    private List<PokemonStatValue> generateFinalStatsFromBase(){
        List<PokemonStat> baseStats = specie.getStats();
        List<PokemonStatValue> finalStats = new ArrayList<>();

        for (PokemonStat stat : baseStats) {
            PokemonStatValue pokemonStatValue = new PokemonStatValue(stat.getStat(), stat.getBaseStat(), level);
            finalStats.add(pokemonStatValue);
        }

        return finalStats;
    }

    public int getFinalStatByIdentifier(String identifier){
        return stats.stream().filter(s -> s.getStat().getIdentifier().equals(identifier))
            .map(PokemonStatValue::getFinalStat)
            .findAny()
            .orElseThrow(() -> new IllegalStateException("Stat with identifier " + identifier + " not found"));
    }

    private int getHPStat(){
        return getFinalStatByIdentifier(HP_STAT);
    }

    public int getAttackStat(){
        return getFinalStatByIdentifier(ATTACK_STAT);
    }

    public int getDefenseStat(){
        return getFinalStatByIdentifier(DEFENSE_STAT);
    }

    public int getSpecialAttackStat() {
        return getFinalStatByIdentifier(SP_ATTACK_STAT);
    }

    public int getSpecialDefenseStat() {
        return getFinalStatByIdentifier(SP_DEFENSE_STAT);
    }

    public int getSpeedStat(){
        return getFinalStatByIdentifier(SPEED_STAT);
    }

    public ActivePokemon(Pokemon specie, String nickname, int level, int currentXp, int nextLevelXp,
                         List<ActiveMove> learnedMoves, boolean isPlayer) {
        this.specie = specie;
        this.nickname = nickname;
        this.level = level;
        this.currentXp = currentXp;
        this.nextLevelXp = nextLevelXp;
        this.learnedMoves = learnedMoves;
        this.stats = generateFinalStatsFromBase();
        this.currentHP = getHPStat();
        this.maxHP = getHPStat();
        this.isFainted = false;
        this.isPlayer = isPlayer;
    }

    public Pokemon getSpecie() {
        return specie;
    }

    public void setSpecie(Pokemon specie) {
        this.specie = specie;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    public int getNextLevelXp() {
        return nextLevelXp;
    }

    public void setNextLevelXp(int nextLevelXp) {
        this.nextLevelXp = nextLevelXp;
    }

    public List<PokemonStatValue> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStatValue> stats) {
        this.stats = stats;
    }

    public List<ActiveMove> getLearnedMoves() {
        return learnedMoves;
    }

    public void setLearnedMoves(List<ActiveMove> learnedMoves) {
        this.learnedMoves = learnedMoves;
    }

    public Map<String, Integer> getStatStages() {
        return statStages;
    }

    public void setStatStages(Map<String, Integer> statStages) {
        this.statStages = statStages;
    }

    public boolean isFainted() {
        return isFainted;
    }

    public void setFainted(boolean fainted) {
        isFainted = fainted;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    @Override
    public String toString() {
        return "ActivePokemon{" +
            "specie=" + specie +
            ", nickname='" + nickname + '\'' +
            ", level=" + level +
            ", currentHP=" + currentHP +
            ", currentXp=" + currentXp +
            ", nextLevelXp=" + nextLevelXp +
            ", stats=" + stats +
            ", learnedMoves=" + learnedMoves +
            ", isFainted=" + isFainted +
            '}';
    }
}

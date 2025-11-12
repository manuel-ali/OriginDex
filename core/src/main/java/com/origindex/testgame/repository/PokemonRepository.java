package com.origindex.testgame.repository;

import com.origindex.testgame.database.DatabaseManager;
import com.origindex.testgame.game.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PokemonRepository {

    public static Pokemon getCompletePokemonById(int pokemonId){
        Pokemon pokemon = getPokemonById(pokemonId);
        if (pokemon == null){
            return null;
        }
        pokemon.setStats(getPokemonStatsById(pokemonId));
        pokemon.setMoves(getPokemonMovesById(pokemonId));
        pokemon.setTypes(getPokemonTypesById(pokemonId));

        return pokemon;
    }

    public static Pokemon getPokemonById(int pokemonId){
        String query = "select id, identifier, species_id, height, weight, base_experience, order_index, is_default " +
            "from pokemon " +
            "where id = ?";

        try (PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, pokemonId);
            try (ResultSet rs = preparedStatement.executeQuery()){
                if (rs.next()){
                    int id = rs.getInt("id");
                    String identifier = rs.getString("identifier");
                    int speciesId = rs.getInt("species_id");
                    int height = rs.getInt("height");
                    int weight = rs.getInt("weight");
                    int baseExperience = rs.getInt("base_experience");
                    Integer orderIndex = DatabaseManager.getNullableInt(rs, "order_index");
                    Boolean isDefault = rs.getObject("is_default", Boolean.class);

                    return new Pokemon(id, identifier, height, weight, baseExperience, orderIndex, isDefault, null, null, null);
                }
            }
        }catch (SQLException e){
            System.out.println("Error al obtener el Pokémon por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static List<PokemonStat> getPokemonStatsById(int pokemonId){
        // Lista para almacenar las estadísticas del Pokémon con ID @pokemonId
        List<PokemonStat> stats = new ArrayList<>();

        // Query para obtener las estadísticas del Pokémon por ID
        String query = "select ps.stat_id, ps.base_stat, ps.effort, " +
            "s.damage_class_id, s.identifier as stat_identifier, s.is_battle_only, s.game_index, " +
            "dc.id as dc_id, dc.identifier as dc_identifier " +
            "from pokemon_stats ps " +
            "join stats s on ps.stat_id = s.id " +
            "left join move_damage_classes dc on s.damage_class_id = dc.id " +
            "where ps.pokemon_id = ?";

        try (PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, pokemonId);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    MoveDamageClass moveDamageClass = null;
                    int damageClassId = rs.getInt("damage_class_id");
                    if (!rs.wasNull()) {
                        moveDamageClass = new MoveDamageClass(
                            damageClassId,
                            rs.getString("dc_identifier")
                        );
                    }
                    Stat stat = new Stat(
                        rs.getInt("stat_id"),
                        moveDamageClass,
                        rs.getString("stat_identifier"),
                        rs.getBoolean("is_battle_only"),
                        rs.getInt("game_index")
                    );
                    PokemonStat pokemonStat = new PokemonStat(
                        stat,
                        rs.getInt("base_stat"),
                        rs.getInt("effort")
                    );
                    // Agregar la estadística a la lista
                    stats.add(pokemonStat);
                }
            }
        }catch (SQLException e){
            System.out.println("Error al obtener las estadísticas del Pokémon por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return stats;
    }

    public static List<PokemonMove> getPokemonMovesById(int pokemonId){
        List<PokemonMove> moves = new ArrayList<>();
        Set<Integer> moveIdsToLoad = new HashSet<>(); //Set para guardar los ID's de los movimientos que se van a cargar en cada PokemonMove
        Map<PokemonMove, Integer> pokemonMoveAssignments = new HashMap<>(); //Map para guardar la relación entre PokemonMove y su el ID de movimiento correspondiente

        String query = "select pm.pokemon_id, pm.version_group_id as pokemon_move_vg_id, pm.move_id, pm.level, pm.pokemon_move_method_id as pm_m_id, pm.order_index as pokemon_move_order_index, pm.mastery,\n" +
            "       vg.id as version_group_id, vg.identifier as version_group_identifier, vg.generation_id as version_group_generation_id, vg.order_index as version_group_order_index,\n" +
            "       g.id as generation_id, g.main_region_id, g.identifier as generation_identifier,\n" +
            "       r.id as region_id, r.identifier as region_identifier,\n" +
            "       pmm.id as pokemon_move_method_id, pmm.identifier as pokemon_move_method_identifier\n" +
            "from pokemon_moves pm\n" +
            "join version_groups vg on pm.version_group_id = vg.id\n" +
            "join generations g on vg.generation_id = g.id\n" +
            "join regions r on g.main_region_id = r.id\n" +
            "join pokemon_move_methods pmm on pm.pokemon_move_method_id = pmm.id\n" +
            "where pm.pokemon_id = ?";

        try(PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, pokemonId);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    Region region = new Region(rs.getInt("region_id"), rs.getString("region_identifier"));
                    Generation generation = new Generation(rs.getInt("generation_id"), region, rs.getString("generation_identifier"));
                    VersionGroup versionGroup = new VersionGroup(rs.getInt("version_group_id"), rs.getString("version_group_identifier"), generation, rs.getInt("version_group_order_index"));
                    PokemonMoveMethod pokemonMoveMethod = new PokemonMoveMethod(rs.getInt("pokemon_move_method_id"), rs.getString("pokemon_move_method_identifier"));

                    int moveId = rs.getInt("move_id");
                    moveIdsToLoad.add(moveId);

                    PokemonMove pokemonMove = new PokemonMove(versionGroup, null, rs.getInt("level"), pokemonMoveMethod, rs.getInt("pokemon_move_order_index"), rs.getInt("mastery"));
                    moves.add(pokemonMove);
                    pokemonMoveAssignments.put(pokemonMove, moveId);
                }
            }

            // Cargar los movimientos en el mapa
            HashMap<Integer, Move> movesToLoad = new HashMap<>();
            for (int moveId : moveIdsToLoad){ //Recorro el set los ID's de los movimientos que tengo que cargar
                movesToLoad.put(moveId, MoveRepository.getCompleteMoveById(moveId));
            }

            // Asignar los movimientos a los PokemonMove
            for (PokemonMove pokemonMove : moves){ //Recorro la lista de PokemonMove
                int moveId = pokemonMoveAssignments.get(pokemonMove); //Obtengo el ID del movimiento correspondiente
                pokemonMove.setMove(movesToLoad.get(moveId)); //Asigno el movimiento al PokemonMove
            }
        }catch (SQLException e){
            System.out.println("Error al obtener los movimientos del Pokémon por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return moves;
    }

    public static List<PokemonType> getPokemonTypesById(int pokemonId){
        List<PokemonType> types = new ArrayList<>();
        String query = "select pt.type_id, pt.slot, " +
            //Datos del Type
            "t.identifier as type_identifier, t.generation_id as type_generation_id, t.damage_class_id as type_damage_class_id, " +
            //Datos de la Generación del Type
            "g.main_region_id as generation_region_id, g.identifier as generation_identifier, " +
            //Datos de la Region de la Generación del Type
            "r.identifier as generation_region_identifier, " +
            //Datos de la DamageClass del Type
            "mdc.identifier as type_damage_class_identifier " +
            "from pokemon_types pt " +
            "join types t on pt.type_id = t.id " +
            "left join generations g on t.generation_id = g.id " +
            "left join regions r on r.id = g.main_region_id " +
            "left join move_damage_classes mdc on mdc.id = t.damage_class_id " +
            "where pokemon_id = ?";

        try(PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, pokemonId);
            try(ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    MoveDamageClass moveDamageClass = null;
                    rs.getInt("type_damage_class_id");
                    if (!rs.wasNull()) {
                        moveDamageClass = new MoveDamageClass(
                            rs.getInt("type_damage_class_id"),
                            rs.getString("type_damage_class_identifier")
                        );
                    }

                    Type type = new Type(
                        rs.getInt("type_id"),
                        rs.getString("type_identifier"),
                        new Generation(
                            rs.getInt("type_generation_id"),
                            new Region(
                                rs.getInt("generation_region_id"),
                                rs.getString("generation_region_identifier")
                            ),
                            rs.getString("generation_identifier")
                        ),
                        moveDamageClass
                    );

                    PokemonType pokemonType = new PokemonType(
                        type,
                        rs.getInt("slot")
                    );

                    types.add(pokemonType);
                }
            }
        }catch (SQLException e){
            System.out.println("Error al obtener los tipos del Pokémon por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return types;
    }
}

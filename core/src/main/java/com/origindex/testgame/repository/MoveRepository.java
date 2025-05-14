package com.origindex.testgame.repository;

import com.origindex.testgame.database.DatabaseManager;
import com.origindex.testgame.game.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MoveRepository {

    public static Move getCompleteMoveById(int moveId) {
        Move move = getMoveById(moveId);
        if (move == null) {
            return null;
        }

        move.setMeta(MoveMetaRepository.getMoveMetaById(moveId));
        move.setStatChanges(getMoveMetaStatChangeById(moveId));
        return move;
    }

    public static Move getMoveById(int moveId) {
        String query = "select m.id as move_id, m.identifier as move_identifier, m.generation_id as move_generation_id, m.type_id as move_type_id, m.power, m.pp, m.accuracy, m.priority, m.target_id as move_target_id, m.damage_class_id as damage_class_id, m.effect_id as move_effect_id, m.effect_chance,\n" +
            "g.id as generation_Id, g.main_region_id, g.identifier as generation_identifier,\n" +
            "r.id as region_id, r.identifier as region_identifier,\n" +
            "mt.id as target_id, mt.identifier as target_identifier,\n" +
            "mdc.id as move_damage_class_id, mdc.identifier as damage_class_identifier\n" +
            "from moves m\n" +
            "join generations g on m.generation_id = g.id\n" +
            "join regions r on g.main_region_id = r.id\n" +
            "join move_targets mt on m.target_id = mt.id\n" +
            "join move_damage_classes mdc on m.damage_class_id = mdc.id\n" +
            "where m.id = ?";

        try(PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, moveId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("move_id");
                String identifier = rs.getString("move_identifier");
                Region region = new Region(rs.getInt("region_id"), rs.getString("region_identifier"));
                Generation generation = new Generation(rs.getInt("generation_Id"), region, rs.getString("generation_identifier"));
                Type type = TypeRepository.getTypeById(rs.getInt("move_type_id"));
                Integer power = DatabaseManager.getNullableInt(rs, "power");
                Integer pp = DatabaseManager.getNullableInt(rs, "pp");
                Integer accuracy = DatabaseManager.getNullableInt(rs, "accuracy");
                int priority = rs.getInt("priority");
                MoveTarget target = new MoveTarget(rs.getInt("target_id"), rs.getString("target_identifier"));
                MoveDamageClass moveDamageClass = new MoveDamageClass(rs.getInt("damage_class_id"), rs.getString("damage_class_identifier"));
                Integer effectId = DatabaseManager.getNullableInt(rs, "move_effect_id");
                Integer effectChance = DatabaseManager.getNullableInt(rs, "effect_chance");

                return new Move(id, identifier, generation, type, power, pp, accuracy, priority, target, moveDamageClass, effectChance, null, null);
            }

        }catch (SQLException e){
            System.out.println("Error al obtener un movimiento por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static List<MoveMetaStatChange> getMoveMetaStatChangeById(int moveId){
        List<MoveMetaStatChange> statChanges = new ArrayList<>();

        String query = "select mmst.move_id, mmst.stat_id , mmst.change,\n" +
            "       s.id, s.damage_class_id, s.identifier as stat_identifier, s.is_battle_only, s.game_index,\n" +
            "       mdc.id as move_damage_class_id, mdc.identifier as move_damage_class_identifier\n" +
            "from move_meta_stat_changes mmst\n" +
            "join stats s on s.id = mmst.stat_id\n" +
            "left join move_damage_classes mdc on mdc.id = s.damage_class_id\n" +
            "where move_id = ?";

        try(PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, moveId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                MoveDamageClass moveDamageClass = null;
                int moveDamageClassId = rs.getInt("damage_class_id");
                if (!rs.wasNull()) {
                    moveDamageClass = new MoveDamageClass(moveDamageClassId, rs.getString("damage_class_identifier"));
                }

                Stat stat = new Stat(rs.getInt("stat_id"), moveDamageClass, rs.getString("stat_identifier"), rs.getBoolean("is_battle_only"), DatabaseManager.getNullableInt(rs, "game_index"));
                int change = rs.getInt("change");
                statChanges.add(new MoveMetaStatChange(stat, change));
            }
        }catch (SQLException e){
            System.out.println("Error al obtener el move meta stat change de un movimiento por su ID: " + e.getMessage());
            e.printStackTrace();
        }

        return statChanges;
    }
}

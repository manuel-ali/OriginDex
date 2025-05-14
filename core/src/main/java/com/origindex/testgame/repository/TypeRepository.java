package com.origindex.testgame.repository;

import com.origindex.testgame.database.DatabaseManager;
import com.origindex.testgame.game.model.Generation;
import com.origindex.testgame.game.model.MoveDamageClass;
import com.origindex.testgame.game.model.Region;
import com.origindex.testgame.game.model.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeRepository {

    public static Type getTypeById(int typeId) {
        String query = "select t.id, t.identifier as type_identifier, t.generation_id as type_generation_id, t.damage_class_id as type_damage_class_id,\n" +
            "       g.id as generation_id, g.main_region_id, g.identifier as generation_identifier,\n" +
            "       r.id as region_id, r.identifier as region_identifier,\n" +
            "    mdc.id as move_damage_class_id, mdc.identifier as move_damage_class_identifier\n" +
            "    from types t\n" +
            "    join generations g on g.id = t.generation_id\n" +
            "    join regions r on g.main_region_id = r.id\n" +
            "    left join move_damage_classes mdc on mdc.id = t.damage_class_id\n" +
            "where t.id = ?";

        try (PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, typeId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String identifier = rs.getString("type_identifier");
                Region region = new Region(rs.getInt("region_id"), rs.getString("region_identifier"));
                Generation generation = new Generation(rs.getInt("generation_id"), region, rs.getString("generation_identifier"));

                MoveDamageClass moveDamageClass = null;
                int moveDamageClassId = rs.getInt("move_damage_class_id");
                if (!rs.wasNull()) {
                    moveDamageClass = new MoveDamageClass(moveDamageClassId, rs.getString("move_damage_class_identifier"));
                }

                return new Type(id, identifier, generation, moveDamageClass);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el tipo por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}

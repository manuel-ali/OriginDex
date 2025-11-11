package com.origindex.testgame.repository;

import com.origindex.testgame.database.DatabaseManager;
import com.origindex.testgame.game.model.Type;
import com.origindex.testgame.game.model.TypeEfficacy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository class for retrieving type effectiveness data from the database.
 * Used to build the complete set of type matchups for battle calculations.
 */
public class TypeEfficacyRepository {

    /**
     * Retrieves all type effectiveness relationships from the database.
     * Each record links an attacking type with a target type and a corresponding damage factor.
     *
     * @return A list of {@link TypeEfficacy} objects representing all type matchups stored in the database.
     */
    public static List<TypeEfficacy> getTypeEfficacies(){
        List<Type> types = TypeRepository.getAllTypes();
        Map<Integer, Type> typeMap = new HashMap<>();
        List<TypeEfficacy> typeEfficacies = new ArrayList<>();

        for (Type type: types){
            typeMap.put(type.getId(), type);
        }

        String query = "SELECT damage_type_id, target_type_id, damage_factor\n" +
            "FROM type_efficacy";

        try(PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()){
                int damageTypeId = rs.getInt("damage_type_id");
                int targetTypeId = rs.getInt("target_type_id");
                int damageFactor = rs.getInt("damage_factor");

                Type damageType = typeMap.get(damageTypeId);
                Type targetType = typeMap.get(targetTypeId);

                typeEfficacies.add(new TypeEfficacy(damageType, targetType, damageFactor));
            }
            return typeEfficacies;
        }catch (SQLException e){
            System.out.println("Error al obtener los type efficacy: " + e.getMessage());
            e.printStackTrace();
        }
        return typeEfficacies;
    }
}

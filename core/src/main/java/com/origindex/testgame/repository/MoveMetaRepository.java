package com.origindex.testgame.repository;

import com.origindex.testgame.database.DatabaseManager;
import com.origindex.testgame.game.model.MoveMeta;
import com.origindex.testgame.game.model.MoveMetaAilment;
import com.origindex.testgame.game.model.MoveMetaCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoveMetaRepository {

    public static MoveMeta getMoveMetaById(int moveID){
        String query = "select move_id, meta_category_id, meta_ailment_id, min_hits, max_hits, min_turns, max_turns, drain, healing, crit_rate, ailment_chance, flinch_chance, stat_chance,\n" +
            "mmc.id as category_id, mmc.identifier as category_identifier,\n" +
            "mma.id as ailment_id, mma.identifier as ailment_identifier\n" +
            "from move_meta mm\n" +
            "join move_meta_categories mmc on mm.meta_category_id = mmc.id\n" +
            "join move_meta_ailments mma on mm.meta_ailment_id = mma.id";

        try (PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, moveID);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                MoveMetaAilment metaAilment = new MoveMetaAilment(rs.getInt("ailment_id"), rs.getString("ailment_identifier"));
                MoveMetaCategory metaCategory = new MoveMetaCategory(rs.getInt("category_id"), rs.getString("category_identifier"));
                Integer minHits = DatabaseManager.getNullableInt(rs, "min_hits");
                Integer maxHits = DatabaseManager.getNullableInt(rs, "max_hits");
                Integer minTurns = DatabaseManager.getNullableInt(rs, "min_turns");
                Integer maxTurns = DatabaseManager.getNullableInt(rs, "max_turns");
                int drain = rs.getInt("drain");
                int healing = rs.getInt("healing");
                int critRate = rs.getInt("crit_rate");
                int ailmentChance = rs.getInt("ailment_chance");
                int flinchChance = rs.getInt("flinch_chance");
                int statChance = rs.getInt("stat_chance");

                return new MoveMeta(metaCategory, metaAilment, minHits, maxHits, minTurns, maxTurns, drain, healing, critRate, ailmentChance, flinchChance, statChance);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}

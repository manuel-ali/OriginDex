package com.origindex.testgame.csvimport;

import com.badlogic.gdx.Gdx;
import com.origindex.testgame.csvimport.csvmodels.*;
import com.origindex.testgame.csvimport.csvmodels.moves.MoveCSV;
import com.origindex.testgame.csvimport.csvmodels.moves.MoveDamageClassCSV;
import com.origindex.testgame.csvimport.csvmodels.moves.MoveTargetCSV;
import com.origindex.testgame.csvimport.csvmodels.pokemon.*;
import com.origindex.testgame.database.DatabaseManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class CSVImporter {
    public static void importAll(){
        try(Connection connection = DatabaseManager.getConnection()){
            importGenerations(connection, "csv/generations.csv");
            importMoveDamageClasses(connection, "csv/moves/move_damage_classes.csv");
            importMoveTargets(connection, "csv/moves/move_targets.csv");
            //importMoves(connection, "csv/moves/moves.csv");
            //importPokemon(connection, "csv/pokemon/pokemon.csv");
            importPokemonMoveMethods(connection, "csv/pokemon/pokemon_move_methods.csv");
            //importPokemonMoves(connection, "csv/pokemon/pokemon_moves.csv");
            //importPokemonSpecies(connection, "csv/pokemon/pokemon_species.csv");
            //importPokemonStats(connection, "csv/pokemon/pokemon_stats.csv");
            //importPokemonTypes(connection, "csv/pokemon/pokemon_types.csv");
            importRegions(connection, "csv/regions.csv");
            importStats(connection, "csv/stats.csv");
            importTypeEfficacy(connection, "csv/type_efficacy.csv");
            importTypes(connection, "csv/types.csv");
            importVersionGroups(connection, "csv/version_groups.csv");
        }catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void importStarter(){
        try (Connection connection = DatabaseManager.getConnection()){
            importStarterPokemon(connection, "csv/pokemon/pokemon.csv");
        }catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void importStarterPokemon(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into pokemon (id, identifier, species_id, height, weight, base_experience, order_index, is_default) values (?,?,?,?,?,?,?,?)";
        List<PokemonCSV> pokemon = new ArrayList<>();
        Set<Integer> validIds = new HashSet<>(Arrays.asList(1, 4, 7));

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);

                if (!validIds.contains(id)){
                    continue;
                }

                String identifier = parseNullableString(parts[1]);
                int speciesId = parseNullableInteger(parts[2]);
                int height = parseNullableInteger(parts[3]);
                int weight = parseNullableInteger(parts[4]);
                int baseExperience = parseNullableInteger(parts[5]);
                Integer orderIndex = parseNullableInteger(parts[6]);
                Boolean isDefault = parseNullableBoolean(parts[7]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.setInt(3, speciesId);
                stmt.setInt(4, height);
                stmt.setInt(5, weight);
                stmt.setInt(6, baseExperience);
                stmt.setObject(7, orderIndex);
                stmt.setObject(8, isDefault);
                stmt.addBatch();

                pokemon.add(new PokemonCSV(id, identifier, speciesId, height, weight, baseExperience, orderIndex, isDefault));
            }

            stmt.executeBatch();
            System.out.println("Importados los starter pokemon: ");
            System.out.println(pokemon);

            importStarterPokemonMoves(connection, "csv/pokemon/pokemon_moves.csv", validIds);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importStarterPokemonMoves(Connection connection, String csvPath, Set<Integer> validPokemonIds) throws SQLException{
        String sql = "insert into pokemon_moves (pokemon_id, version_group_id, move_id, level, pokemon_move_method_id, order_index, mastery) values (?,?,?,?,?,?,?)";
        List<PokemonMovesCSV> pokemonMoves = new ArrayList<>();
        Set<Integer> validMovesIds = new HashSet<>(12);
        Map<Integer, Integer> pokemonMovesCounter = new HashMap<>();

        for (int id : validPokemonIds){
            pokemonMovesCounter.put(id, 0);
        }

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",", -1);

                int pokemonId = parseNullableInteger(parts[0]);

                if (!pokemonMovesCounter.containsKey(pokemonId) || pokemonMovesCounter.get(pokemonId) >= 4) {
                    continue;
                }

                int versionGroupId = parseNullableInteger(parts[1]);
                int moveId = parseNullableInteger(parts[2]);
                int level = parseNullableInteger(parts[3]);
                int pokemonMoveMethodId = parseNullableInteger(parts[4]);
                Integer orderIndex = parseNullableInteger(parts[5]);
                Boolean mastery = parseNullableBoolean(parts[6]);

                stmt.setInt(1, pokemonId);
                stmt.setInt(2, versionGroupId);
                stmt.setInt(3, moveId);
                stmt.setInt(4, level);
                stmt.setInt(5, pokemonMoveMethodId);
                stmt.setObject(6, orderIndex);
                stmt.setObject(7, mastery);
                stmt.addBatch();

                pokemonMoves.add(new PokemonMovesCSV(pokemonId, versionGroupId, moveId, level, pokemonMoveMethodId, orderIndex, mastery));
                validMovesIds.add(moveId);
                pokemonMovesCounter.put(pokemonId, pokemonMovesCounter.get(pokemonId) + 1);

                if (pokemonMovesCounter.values().stream().allMatch(count -> count >= 4)) {
                    break;
                }
            }

            stmt.executeBatch();
            System.out.println("Importados todos los pokemon moves: ");
            System.out.println(pokemonMoves);

            importStarterMoves(connection, "csv/moves/moves.csv", validMovesIds);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importStarterMoves(Connection connection, String csvPath, Set<Integer> validMovesIds) throws SQLException{
        String sql = "insert into moves (id, identifier, generation_id, type_id, power, pp," +
            "accuracy, priority, target_id, damage_class_id, effect_id, effect_chance) " +
            "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        List<MoveCSV> moves = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;
            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);

                if (!validMovesIds.contains(id)){
                    continue;
                }

                String identifier = parseNullableString(parts[1]);
                int generationId = parseNullableInteger(parts[2]);
                int typeId = parseNullableInteger(parts[3]);
                Integer power = parseNullableInteger(parts[4]);
                Integer pp = parseNullableInteger(parts[5]);
                Integer accuracy = parseNullableInteger(parts[6]);
                int priority = parseNullableInteger(parts[7]);
                int targetId = parseNullableInteger(parts[8]);
                int damageClassId = parseNullableInteger(parts[9]);
                Integer effectId = parseNullableInteger(parts[10]);
                Integer effectChance = parseNullableInteger(parts[11]);


                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.setInt(3, generationId);
                stmt.setInt(4, typeId);
                stmt.setObject(5, power);
                stmt.setObject(6, pp);
                stmt.setObject(7, accuracy);
                stmt.setInt(8, priority);
                stmt.setInt(9, targetId);
                stmt.setInt(10, damageClassId);
                stmt.setObject(11, effectId);
                stmt.setObject(12, effectChance);
                stmt.addBatch();

                moves.add(new MoveCSV(id, identifier, generationId, typeId, power, pp,
                    accuracy, priority, targetId, damageClassId, effectId, effectChance));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los moves: ");
            System.out.println(moves);
        }catch (IOException e){
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importGenerations(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into generations (id, main_region_id, identifier) values (?,?,?)";
        List<GenerationCSV> generations = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                int mainRegionId = parseNullableInteger(parts[1]);
                String identifier = parseNullableString(parts[2]);

                stmt.setInt(1, id);
                stmt.setInt(2, mainRegionId);
                stmt.setString(3, identifier);
                stmt.addBatch();

                generations.add(new GenerationCSV(id, mainRegionId, identifier));
            }

            stmt.executeBatch();
            System.out.println("Importadas todas las generations: ");
            System.out.println(generations);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importMoveDamageClasses(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into move_damage_classes (id, identifier) values (?,?)";
        List<MoveDamageClassCSV> moveDamageClasses = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.addBatch();

                moveDamageClasses.add(new MoveDamageClassCSV(id, identifier));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los move damage classes: ");
            System.out.println(moveDamageClasses);
        }catch (IOException e){
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importMoveTargets(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into move_targets (id, identifier) values (?,?)";
        List<MoveTargetCSV> moveTargets = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;
            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.addBatch();

                moveTargets.add(new MoveTargetCSV(id, identifier));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los move targets: ");
            System.out.println(moveTargets);
        }catch (IOException e){
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importMoves(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into moves (id, identifier, generation_id, type_id, power, pp," +
            "accuracy, priority, target_id, damage_class_id, effect_id, effect_chance) " +
            "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        List<MoveCSV> moves = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;
            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);
                int generationId = parseNullableInteger(parts[2]);
                int typeId = parseNullableInteger(parts[3]);
                Integer power = parseNullableInteger(parts[4]);
                Integer pp = parseNullableInteger(parts[5]);
                Integer accuracy = parseNullableInteger(parts[6]);
                int priority = parseNullableInteger(parts[7]);
                int targetId = parseNullableInteger(parts[8]);
                int damageClassId = parseNullableInteger(parts[9]);
                Integer effectId = parseNullableInteger(parts[10]);
                Integer effectChance = parseNullableInteger(parts[11]);


                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.setInt(3, generationId);
                stmt.setInt(4, typeId);
                stmt.setObject(5, power);
                stmt.setObject(6, pp);
                stmt.setObject(7, accuracy);
                stmt.setInt(8, priority);
                stmt.setInt(9, targetId);
                stmt.setInt(10, damageClassId);
                stmt.setObject(11, effectId);
                stmt.setObject(12, effectChance);
                stmt.addBatch();

                moves.add(new MoveCSV(id, identifier, generationId, typeId, power, pp,
                    accuracy, priority, targetId, damageClassId, effectId, effectChance));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los moves: ");
            System.out.println(moves);
        }catch (IOException e){
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importPokemon(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into pokemon (id, identifier, species_id, height, weight, base_experience, order_index, is_default) values (?,?,?,?,?,?,?,?)";
        List<PokemonCSV> pokemon = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);
                int speciesId = parseNullableInteger(parts[2]);
                int height = parseNullableInteger(parts[3]);
                int weight = parseNullableInteger(parts[4]);
                int baseExperience = parseNullableInteger(parts[5]);
                Integer orderIndex = parseNullableInteger(parts[6]);
                Boolean isDefault = parseNullableBoolean(parts[7]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.setInt(3, speciesId);
                stmt.setInt(4, height);
                stmt.setInt(5, weight);
                stmt.setInt(6, baseExperience);
                stmt.setObject(7, orderIndex);
                stmt.setObject(8, isDefault);
                stmt.addBatch();

                pokemon.add(new PokemonCSV(id, identifier, speciesId, height, weight, baseExperience, orderIndex, isDefault));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los pokemon: ");
            System.out.println(pokemon);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importPokemonMoveMethods(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into pokemon_move_methods (id, identifier) values (?,?)";
        List<PokemonMoveMethodCSV> pokemonMoveMethods = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.addBatch();

                pokemonMoveMethods.add(new PokemonMoveMethodCSV(id, identifier));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los pokemon move methods: ");
            System.out.println(pokemonMoveMethods);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importPokemonMoves(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into pokemon_moves (pokemon_id, version_group_id, move_id, level, pokemon_move_method_id, order_index, mastery) values (?,?,?,?,?,?,?)";
        List<PokemonMovesCSV> pokemonMoves = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int pokemonId = parseNullableInteger(parts[0]);
                int versionGroupId = parseNullableInteger(parts[1]);
                int moveId = parseNullableInteger(parts[2]);
                int level = parseNullableInteger(parts[3]);
                int pokemonMoveMethodId = parseNullableInteger(parts[4]);
                Integer orderIndex = parseNullableInteger(parts[5]);
                Boolean mastery = parseNullableBoolean(parts[6]);

                stmt.setInt(1, pokemonId);
                stmt.setInt(2, versionGroupId);
                stmt.setInt(3, moveId);
                stmt.setInt(4, level);
                stmt.setInt(5, pokemonMoveMethodId);
                stmt.setObject(6, orderIndex);
                stmt.setObject(7, mastery);
                stmt.addBatch();

                pokemonMoves.add(new PokemonMovesCSV(pokemonId, versionGroupId, moveId, level, pokemonMoveMethodId, orderIndex, mastery));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los pokemon moves: ");
            System.out.println(pokemonMoves);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importPokemonSpecies(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into pokemon_species (id, identifier, generation_id, evolves_from_species_id, evolution_chain_id, habitat_id, " +
            "gender_rate, capture_rate, base_happiness, is_baby, hatch_counter, has_gender_differences, " +
            "growth_rate_id, forms_switchable, is_legendary, is_mythical, order_index, conquest_order)" +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        List<PokemonSpeciesCSV> pokemonSpecies = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);
                int generationId = parseNullableInteger(parts[2]);
                Integer evolvesFromSpeciesId = parseNullableInteger(parts[3]);
                int evolutionChainId = parseNullableInteger(parts[4]);
                Integer habitatId = parseNullableInteger(parts[5]);
                int genderRate = parseNullableInteger(parts[6]);
                int captureRate = parseNullableInteger(parts[7]);
                int baseHappiness = parseNullableInteger(parts[8]);
                boolean isBaby = parseNullableBoolean(parts[9]);
                int hatchCounter = parseNullableInteger(parts[10]);
                boolean hasGenderDifferences = parseNullableBoolean(parts[11]);
                int growthRateId = parseNullableInteger(parts[12]);
                boolean formsSwitchable = parseNullableBoolean(parts[13]);
                boolean isLegendary = parseNullableBoolean(parts[14]);
                boolean isMythical = parseNullableBoolean(parts[15]);
                int orderIndex = parseNullableInteger(parts[16]);
                Integer conquestOrder = parseNullableInteger(parts[17]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.setInt(3, generationId);
                stmt.setObject(4, evolvesFromSpeciesId);
                stmt.setInt(5, evolutionChainId);
                stmt.setObject(6, habitatId);
                stmt.setInt(7, genderRate);
                stmt.setInt(8, captureRate);
                stmt.setInt(9, baseHappiness);
                stmt.setBoolean(10, isBaby);
                stmt.setInt(11, hatchCounter);
                stmt.setBoolean(12, hasGenderDifferences);
                stmt.setInt(13, growthRateId);
                stmt.setBoolean(14, formsSwitchable);
                stmt.setBoolean(15, isLegendary);
                stmt.setBoolean(16, isMythical);
                stmt.setInt(17, orderIndex);
                stmt.setObject(18, conquestOrder);
                stmt.addBatch();

                pokemonSpecies.add(new PokemonSpeciesCSV(id, identifier, generationId, evolvesFromSpeciesId, evolutionChainId,
                    habitatId, genderRate, captureRate, baseHappiness, isBaby,
                    hatchCounter, hasGenderDifferences, growthRateId, formsSwitchable, isLegendary,
                    isMythical, orderIndex, conquestOrder));
            }

            stmt.executeBatch();
            System.out.println("Importadas todas las pokemon species: ");
            System.out.println(pokemonSpecies);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importPokemonStats(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into pokemon_stats (pokemon_id, stat_id, base_stat, effort) values (?,?,?,?)";
        List<PokemonStatCSV> pokemonStats = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int pokemonId = parseNullableInteger(parts[0]);
                int statId = parseNullableInteger(parts[1]);
                int baseStat = parseNullableInteger(parts[2]);
                int effort = parseNullableInteger(parts[3]);

                stmt.setInt(1, pokemonId);
                stmt.setInt(2, statId);
                stmt.setInt(3, baseStat);
                stmt.setInt(4, effort);
                stmt.addBatch();

                pokemonStats.add(new PokemonStatCSV(pokemonId, statId, baseStat, effort));
            }

            stmt.executeBatch();
            System.out.println("Importadas todas las pokemon stats: ");
            System.out.println(pokemonStats);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importPokemonTypes(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into pokemon_types (pokemon_id, type_id, slot) values (?,?,?)";
        List<PokemonTypeCSV> pokemonTypes = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                int typeId = parseNullableInteger(parts[1]);
                int slot = parseNullableInteger(parts[2]);

                stmt.setInt(1, id);
                stmt.setInt(2, typeId);
                stmt.setInt(3, slot);
                stmt.addBatch();

                pokemonTypes.add(new PokemonTypeCSV(id, typeId, slot));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los pokemon types: ");
            System.out.println(pokemonTypes);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importRegions(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into regions (id, identifier) values (?,?)";
        List<RegionCSV> regions = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.addBatch();

                regions.add(new RegionCSV(id, identifier));
            }

            stmt.executeBatch();
            System.out.println("Importadas todas las regiones: ");
            System.out.println(regions);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importStats(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into stats (id, damage_class_id, identifier, is_battle_only, game_index) values (?,?,?,?,?)";
        List<StatCSV> stats = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",", -1);

                int id = parseNullableInteger(parts[0]);
                Integer damageClassId = parseNullableInteger(parts[1]);
                String identifier = parseNullableString(parts[2]);
                boolean isBattleOnly = parseNullableBoolean(parts[3]);
                Integer gameIndex = parseNullableInteger(parts[4]);

                stmt.setInt(1, id);
                stmt.setObject(2, damageClassId);
                stmt.setString(3, identifier);
                stmt.setBoolean(4, isBattleOnly);
                stmt.setObject(5, gameIndex);
                stmt.addBatch();

                stats.add(new StatCSV(id, damageClassId, identifier, isBattleOnly, gameIndex));
            }

            stmt.executeBatch();
            System.out.println("Importadas todas las stats: ");
            System.out.println(stats);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importTypeEfficacy(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into type_efficacy (damage_type_id, target_type_id, damage_factor) values (?,?,?)";
        List<TypeEfficacyCSV> typeEfficacy = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int damageTypeId = parseNullableInteger(parts[0]);
                int targetTypeId = parseNullableInteger(parts[1]);
                int damageFactor = parseNullableInteger(parts[2]);

                stmt.setInt(1, damageTypeId);
                stmt.setInt(2, targetTypeId);
                stmt.setInt(3, damageFactor);
                stmt.addBatch();

                typeEfficacy.add(new TypeEfficacyCSV(damageTypeId, targetTypeId, damageFactor));
            }

            stmt.executeBatch();
            System.out.println("Importadas todas las type efficacy: ");
            System.out.println(typeEfficacy);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importTypes(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into types (id, identifier, generation_id, damage_class_id) values (?,?,?,?)";
        List<TypeCSV> types = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",", -1);

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);
                int generationId = parseNullableInteger(parts[2]);
                Integer damageClassId = parseNullableInteger(parts[3]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.setInt(3, generationId);
                stmt.setObject(4, damageClassId);
                stmt.addBatch();

                types.add(new TypeCSV(id, identifier, generationId, damageClassId));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los types: ");
            System.out.println(types);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    public static void importVersionGroups(Connection connection, String csvPath) throws SQLException{
        String sql = "insert into version_groups (id, identifier, generation_id, order_index) values (?,?,?,?)";
        List<VersionGroupCSV> versionGroups = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(sql);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(Gdx.files.internal(csvPath).read()))
        ){
            String line;
            boolean skipHeader = true;

            while ((line = bfr.readLine()) != null){
                if (skipHeader){
                    skipHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                int id = parseNullableInteger(parts[0]);
                String identifier = parseNullableString(parts[1]);
                int generationId = parseNullableInteger(parts[2]);
                int orderIndex = parseNullableInteger(parts[3]);

                stmt.setInt(1, id);
                stmt.setString(2, identifier);
                stmt.setInt(3, generationId);
                stmt.setInt(4, orderIndex);
                stmt.addBatch();

                versionGroups.add(new VersionGroupCSV(id, identifier, generationId, orderIndex));
            }

            stmt.executeBatch();
            System.out.println("Importados todos los version groups: ");
            System.out.println(versionGroups);
        }catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    /**
     * Método para convertir un String a un Integer o a Null, devolviendo null si el String es vacío o "null".
     * @param value El String a convertir.
     * @return El Integer convertido o null si el String es vacío o "null".
     */
    private static Integer parseNullableInteger(String value) {
        //Comprobar si el valor es null o vacío
        if (value == null || value.trim().isEmpty() || value.equals("null")) {
            return null;  // Devolvemos NULL
        }

        try {
            return Integer.parseInt(value); // Devolver el valor como un Integer
        } catch (NumberFormatException e) {
            return null;  // Si no es un valor válido, devolvemos NULL
        }
    }

    /*
     * Método para convertir un String a un Null, devolviendo null si el String es vacío o "null".
     * @param value El String a convertir.
     * @return El Boolean convertido o null si el String es vacío o "null".
     */
    private static String parseNullableString(String value) {
        //Comprobar si el valor es null o vacío
        if (value == null || value.trim().isEmpty() || value.equals("null")) {
            return null;  // Devolvemos NULL
        }
        return value;  // Devolver el valor como está
    }

    /**
     * Método para convertir un String a un Boolean, devolviendo null si el String es vacío o "null".
     * @param value El String a convertir.
     * @return El Boolean convertido o null si el String es vacío o "null".
     */
    private static Boolean parseNullableBoolean(String value) {
        //Comprobar si el valor es null o vacío
        if (value == null || value.trim().isEmpty() || value.equals("null")) {
            return null;  // Devolvemos NULL
        }

        //Comprobar si el valor es un booleano
        if (value.equalsIgnoreCase("0") || value.equalsIgnoreCase("1")) {
            return Boolean.parseBoolean(value); // Devolvemos el valor del String convertido a booleano
        }

        return null;  // Si no es un valor válido, devolvemos NULL
    }
}

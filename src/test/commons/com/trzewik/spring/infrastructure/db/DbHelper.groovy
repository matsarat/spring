package com.trzewik.spring.infrastructure.db

import com.trzewik.spring.domain.game.Game
import com.trzewik.spring.domain.player.Player
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.testcontainers.containers.PostgreSQLContainer

@Slf4j
class DbHelper {
    private Sql sql
    private static String defaultSchema

    DbHelper(PostgreSQLContainer container, String defaultSchema) {
        sql = Sql.newInstance(
            container.jdbcUrl,
            container.username,
            container.password
        )
        this.defaultSchema = defaultSchema
    }

    List<GroovyRowResult> getAllGames() {
        String query = "SELECT * FROM $gameTable"
        log.info(query)
        sql.rows(query)
    }

    void deleteGames() {
        String query = "DELETE FROM $gameTable"
        log.info(query)
        sql.execute(query)
    }

    List<List<Object>> save(Game game) {
        String query = "INSERT INTO $gameTable (id, deck, status, current_player_id, croupier_id) VALUES (?, ?, ?, ?, ?)"
        log.info(query)
        sql.executeInsert(query.toString(), [game.id, '{}', game.status.name(), game.currentPlayer?.id, game.croupier?.id])
    }

    List<GroovyRowResult> getAllPlayers() {
        String query = "SELECT * FROM $playerTable"
        log.info(query)
        sql.rows(query)
    }

    void deletePlayers() {
        String query = "DELETE FROM $playerTable"
        log.info(query)
        sql.execute(query)
    }

    List<List<Object>> save(Player player) {
        String query = "INSERT INTO $playerTable (id, name) VALUES (?, ?)"
        log.info(query)
        sql.executeInsert(query.toString(), [player.id, player.name])
    }

    List<GroovyRowResult> getAllPlayerGames() {
        String query = "SELECT * FROM $playerGameTable"
        log.info(query)
        sql.rows(query)
    }

    void deletePlayerGames() {
        String query = "DELETE FROM $playerGameTable"
        log.info(query)
        sql.execute(query)
    }

    List<List<Object>> save(String gameId, Player player) {
        String query = "INSERT INTO $playerGameTable (game_id, player_id, hand, move) VALUES (?, ?, ?, ?)"
        log.info(query)
        sql.executeInsert(query.toString(), [gameId, player.id, '{}', player.move.name()])
    }

    private static String getGameTable() {
        return table('game')
    }

    private static String getPlayerTable() {
        return table('player')
    }

    private static String getPlayerGameTable() {
        return table('player_game')
    }

    private static String table(String name) {
        return "$defaultSchema.$name".toString()
    }
}

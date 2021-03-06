package com.trzewik.spring.domain.game

trait GameCommandCreation {

    GameService.MakeGameMoveCommand createMakeGameMoveCommand(MakeGameMoveCommandCreator creator = new MakeGameMoveCommandCreator()) {
        return new GameService.MakeGameMoveCommand(
            creator.gameId,
            creator.playerId,
            creator.move
        )
    }

    GameService.AddPlayerToGameCommand createAddPlayerToGameCommand(AddPlayerToGameCommandCreator creator = new AddPlayerToGameCommandCreator()) {
        return new GameService.AddPlayerToGameCommand(
            creator.gameId,
            creator.playerId
        )
    }

    GameService.CreateGameCommand createCreateGameCommand(CreateGameCommandCreator creator = new CreateGameCommandCreator()) {
        return new GameService.CreateGameCommand()
    }

    GameService.StartGameCommand createStartGameCommand(StartGameCommandCreator creator = new StartGameCommandCreator()) {
        return new GameService.StartGameCommand(
            creator.gameId
        )
    }

    GameService.GetGameResultsCommand createGetGameResultsCommand(GetGameResultsCommandCreator creator = new GetGameResultsCommandCreator()) {
        return new GameService.GetGameResultsCommand(
            creator.gameId
        )
    }

    static class MakeGameMoveCommandCreator {
        String gameId = 'example-game-id'
        String playerId = 'example-player-id'
        Game.Move move = Game.Move.STAND

        MakeGameMoveCommandCreator() {}

        MakeGameMoveCommandCreator(Game game, PlayerInGame player, Game.Move move) {
            this.gameId = game.id
            this.playerId = player.playerId
            this.move = move
        }
    }

    static class AddPlayerToGameCommandCreator {
        String gameId = 'example-game-id'
        String playerId = 'example-player-id'

        AddPlayerToGameCommandCreator() {}

        AddPlayerToGameCommandCreator(Game game, PlayerInGame player) {
            this.gameId = game.id
            this.playerId = player.playerId
        }
    }

    static class StartGameCommandCreator {
        String gameId = 'example-game-id'
    }

    static class GetGameResultsCommandCreator {
        String gameId = 'example-game-id'
    }

    static class CreateGameCommandCreator {

    }
}

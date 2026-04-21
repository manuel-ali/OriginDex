package com.origindex.testgame.view.battle;

import com.origindex.testgame.game.battle.BattleStep;
import com.origindex.testgame.view.battle.sprites.MoveData;

import java.util.List;
import java.util.Queue;

public interface BattleListener {
    void onTurnFinished();
    void executeSteps(Queue<BattleStep> steps);
    void onShowMoves(List<MoveData> moves);
    void onShowMessage(String message);
    void onBattleEnded();
}

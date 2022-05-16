package game;

import game.activity.ActivityCalibrationBlack;
import game.activity.ActivityCalibrationWhite;
import game.activity.ActivityRun;
import game.activity.ActivityRunPID;
import game.guard.GuardTouch;

/**
 * 競技クラス
 * @author 後藤 聡文
 *
 */
public class Game {
    /** タスク呼出回数 */
    private int count = 0;

    /** 競技状態 */
    private State state;

    /** 競技が終了したか */
    private boolean isOver = false;

    /**
     * コンストラクタ
     */
    public Game() {
        StateCalibrationWhite.getInstance().add(new GuardTouch(), new ActivityCalibrationWhite());

        StateCalibrationBlack.getInstance().add(new GuardTouch(), new ActivityCalibrationBlack());

        StateWaitStart.getInstance().add(new GuardTouch(), new ActivityRun(0, 0));

        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.01f , 0.01f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.02f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.03f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.04f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.05f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.06f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.07f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.08f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.09f , 0.0f));
        StateRun.getInstance().add(new GuardTouch(), new ActivityRunPID(200, 0.0f , 300f , 0.10f , 0.0f));


        StateEnd.getInstance().add(new GuardTouch(), new ActivityRun(0, 0));

        changeState(null, StateCalibrationWhite.getInstance());
    }

    /**
     * 実施する
     */
    public void run() {
        if(isOver == false){
            count++;
            if (state instanceof StateEnd) {
                isOver = true;
            } else {
                state.doActivity(this);
            }
        }
    }

    /**
     * タスク呼出回数を取得する
     * @return タスク呼出回数
     */
    public int getCount() {
        return count;
    }

    /**
     * 競技状態を遷移する
     * @param oldState 前状態
     * @param newState　後状態
     */
    public void changeState(State oldState, State newState) {
        this.state = newState;

        if(oldState != null){
            oldState.exitAction();
        }
        if(newState != null){
            newState.entryAction();;
        }
    }

    /**
     * 競技が終了したか
     * @return 競技が終了した場合はtrue
     */
    public boolean isOver() {
        return isOver;
    }

    /**
     * オブジェクトの文字列表現を取得する
     */
    @Override
    public String toString() {
        return state.toString();
    }

}

package bridge;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    public static final String IMMOVABLE = "Can't move";
    public static final String FINISH = "Finish";
    public static final String NORMAL = "Normal";
    public static final int START_POSITION = 0;
    public static final int FIRST = 1;
    private final List<String> bridge;
    private String state;
    private int position;
    private int attempt;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
        this.state = NORMAL;
        this.attempt = FIRST;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String selectedDirection) {
        checkMovable(bridge.get(position), selectedDirection);
        goForward();
        checkFinished();
    }

    private void checkMovable(String movableDirection, String selectedDirection) {
        if (notEquals(movableDirection, selectedDirection)) {
            state = IMMOVABLE;
        }
    }

    private boolean notEquals(String operand1, String operand2) {
        return !operand1.equals(operand2);
    }

    private void goForward() {
        position++;
    }

    private void checkFinished() {
        if (state.equals(IMMOVABLE)) {
            return;
        }
        if (position == bridge.size()) {
            state = FINISH;
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        resetPlayerPosition();
        increaseAttempts();
    }

    private void resetPlayerPosition() {
        position = START_POSITION;
    }

    private void increaseAttempts() {
        attempt++;
    }

    public boolean isNormal() {
        return state.equals(NORMAL);
    }

    public boolean isFinished() {
        return state.equals(FINISH);
    }

    public boolean isImmovable() {
        return state.equals(IMMOVABLE);
    }
}

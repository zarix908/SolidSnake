package app;

import javafx.animation.AnimationTimer;

public abstract class AnimationTimerExt extends AnimationTimer {

    private long _sleepNs = 0;
    private long _prevTime = 0;

    public AnimationTimerExt(long sleepMs) {
        _sleepNs = sleepMs * 1_000_000;
    }

    @Override
    public void handle(long now) {

        if ((now - _prevTime) < _sleepNs) {
            return;
        }

        _prevTime = now;
        handle();
    }

    public abstract void handle();
}
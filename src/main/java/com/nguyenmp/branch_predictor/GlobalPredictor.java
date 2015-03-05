package com.nguyenmp.branch_predictor;

import org.jetbrains.annotations.NotNull;

public class GlobalPredictor implements BranchPredictor {
    private int history;
    private final byte[] counters = new byte[4096];

    @NotNull
    @Override
    public BranchPrediction getPrediction(long pc) {
        return counters[((int) (history ^ (pc >> 2 & 4095)))] > 0 ? BranchPrediction.Taken : BranchPrediction.Not_Taken;
    }

    @Override
    public void update(long pc, BranchPrediction branchOutcome) {
        int index = (int) (history ^ (pc >> 2 & 4095));
        if (branchOutcome == BranchPrediction.Taken) {
            counters[index]++;
        } else {
            counters[index]--;
        }

        if (counters[index] > 3) counters[index] = 3;
        else if (counters[index] < 0) counters[index] = 0;

        history = (((history << 1) | branchOutcome.toInt()) & 4095);
    }
}

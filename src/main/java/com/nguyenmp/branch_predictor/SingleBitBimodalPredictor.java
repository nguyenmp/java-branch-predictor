package com.nguyenmp.branch_predictor;

import org.jetbrains.annotations.NotNull;

public class SingleBitBimodalPredictor implements BranchPredictor {
    private final BranchPrediction[] counters;

    public SingleBitBimodalPredictor(int capacity) {
        counters = new BranchPrediction[capacity];
    }

    @NotNull
    @Override
    public BranchPrediction getPrediction(long pc) {
        BranchPrediction result = counters[((int) ((pc >> 2) % counters.length))];
        return result == null ? BranchPrediction.Taken : result;
    }

    @Override
    public void update(long pc, BranchPrediction branchOutcome) {
        counters[((int) ((pc >> 2) % counters.length))] = branchOutcome;
    }
}

package com.nguyenmp.branch_predictor;

import org.jetbrains.annotations.NotNull;

public interface BranchPredictor {
    @NotNull
    public BranchPrediction getPrediction(long pc);

    public void update(long pc, BranchPrediction branchOutcome);
}

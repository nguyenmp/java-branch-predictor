package com.nguyenmp.branch_predictor;

import org.jetbrains.annotations.NotNull;

public enum BranchPrediction {
    Taken(1), Not_Taken(0);

    private final int value;
    BranchPrediction(int value) {
        this.value = value;
    }

    public boolean equals(int value) {
        return this.value == value;
    }

    @NotNull
    public static BranchPrediction fromInt(int value) {
        switch (value) {
            case 1: return BranchPrediction.Taken;
            case 0: return BranchPrediction.Not_Taken;
            default: assert false; return null;
        }
    }
}

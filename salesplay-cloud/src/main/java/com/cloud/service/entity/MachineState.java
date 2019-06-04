package com.cloud.service.entity;

public enum MachineState {
    pending,
    running,
    terminated,
    stopping,
    stopped,
    unknown,
    starting;

    public static MachineState getIfPresent(String name) {
        for (MachineState machineState: MachineState.values()) {
            if (machineState.name().equalsIgnoreCase(name)) {
                return machineState;
            }
        }
        return MachineState.unknown;
    }
}
package org.qme.utils;

import org.qme.client.Application;
import org.qme.io.Logger;
import org.qme.io.Severity;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for storing data about the runtime that can be accessed later for debugging
 * This includes code for timings.
 * @author cameron
 * @since 0.3.0
 */
public class Performance {

    public static final String GAME_VERSION = Application.class.getPackage().getSpecificationVersion();
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String JAVA_VENDOR = System.getProperty("java.vendor");
    public static final String OPERATING_SYSTEM = System.getProperty("os.name");
    public static final String OPERATING_SYSTEM_VERSION = System.getProperty("os.version");
    public static final String ARCH_TYPE = System.getProperty("os.arch");
    public static String GPU_NAME;
    public static String GPU_VENDOR;
    public static String CPU;

    private static HashMap<String, Long> timings = new HashMap<>();
    private static HashMap<String, Long> durations = new HashMap<>();
    private static long startTime = System.nanoTime();

    /**
     * Rechecks system information
     * This should only be called once on startup
     */
    public static void updateValues() {

        if (GAME_VERSION == null) {
            Logger.log("Could not detect game version! The jar you are running was not compiled properly.", Severity.WARNING);
        }

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        GraphicsCard gpu = hal.getGraphicsCards().get(0);

        GPU_NAME = gpu.getName();
        GPU_VENDOR = gpu.getVendor();
        CPU = cpu.getProcessorIdentifier().getName();

    }

    /**
     * Starts profiling a frame
     */
    public static void beginFrame() {
        timings.clear();
        durations.clear();
        startTime = System.nanoTime();
    }

    /**
     * Starts timing a certain event
     * @param timing the name of the event
     */
    public static void startTiming(String timing) {
        timings.put(timing, System.nanoTime());
    }

    /**
     * Finishes timing a certain event
     * @param timing the name of the event
     */
    public static void endTiming(String timing) {
        if (!timing.contains(timing)) {
            throw new IllegalStateException("Timing ended before its creation");
        }
        durations.put(timing, System.nanoTime() - timings.get(timing));
        timings.remove(timing);
    }


    /**
     * Gets the timings
     * @return hashmap containing the duration in millis of each event
     */
    public static HashMap<String, Float> getTimings() {
        HashMap<String, Float> report = new HashMap<>();
        for (Map.Entry<String, Long> pair : durations.entrySet()) {
            report.put(pair.getKey(), (float) pair.getValue() / 1000000);
        }
        return report;
    }

    /**
     * Gets the total time since the start of this frame
     * @return the time since the start of the frame
     */
    public static float getTotal() {
        return (float) (System.nanoTime() - startTime) / 1000000;
    }
    
}

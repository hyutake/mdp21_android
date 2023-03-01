package com.example.mdp_grp_21;

import android.util.Log;

// To translate the relayed path given from algo to rpi and then to android to update robot position in "real-time"
// Basically will involve converting stm commands into its 'equivalent' on the 20x20 grid map
public class PathTranslator {
    private static final String TAG = "PathTranslator";
    private static GridMap gridMap;

    private static final int CELL_LENGTH = 10;
    private static final int MILLI_DELAY = 200;    // delay between movement commands = 200ms
    private static final int TURNING_RADIUS = 33;   // to estimate the cells covered in an executed turn

    public PathTranslator() {
        this.gridMap = MainActivity.getGridMap();
    }

    public PathTranslator(GridMap gridMap) {
        this.gridMap = gridMap;
    }

    public static void translatePath(String stmCommand) {
        showLog("Entered translatePath");
        char commandType = stmCommand.charAt(0);
        int commandValue = Integer.parseInt(stmCommand.substring(1));
        int moves = 0;
        switch(commandType) {
            case 'f':   // forward
                moves = commandValue / CELL_LENGTH;  // each cell is (assumed) to be 10 (cm?) long
                for(int i = 0; i < moves; i++) {
                    gridMap.moveRobot("forward");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                break;
            case 'b':   // backwards
                moves = commandValue / CELL_LENGTH;  // each cell is (assumed) to be 10 (cm?) long
                for(int i = 0; i < moves; i++) {
                    gridMap.moveRobot("back");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                break;
            case 'a':   // 90 deg right - I assume that the value will always just be '045'
                moves = TURNING_RADIUS / CELL_LENGTH;   // floor div. of turning radius against cell len
                // forward movement
                for(int i = 0; i < moves - 1; i++) {
                    gridMap.moveRobot("forward");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                // right movement
                gridMap.moveRobot("right");
                try {
                    Thread.sleep(MILLI_DELAY);
                } catch(InterruptedException e) {
                    showLog("InterruptedException occurred when calling Thread.sleep()!");
                    e.printStackTrace();
                }
                // forward movement
                for(int i = 0; i < moves - 2; i++) {
                    gridMap.moveRobot("forward");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                break;
            case 'd':   // 90 deg left - I assume that the value will always just be '045'
                moves = TURNING_RADIUS / CELL_LENGTH;   // floor div. of turning radius against cell len
                // forward movement
                for(int i = 0; i < moves - 1; i++) {
                    gridMap.moveRobot("forward");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                // left movement
                gridMap.moveRobot("left");
                try {
                    Thread.sleep(MILLI_DELAY);
                } catch(InterruptedException e) {
                    showLog("InterruptedException occurred when calling Thread.sleep()!");
                    e.printStackTrace();
                }
                // forward movement
                for(int i = 0; i < moves - 2; i++) {
                    gridMap.moveRobot("forward");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                break;
            case 'q':   // 90 deg back-left - I assume that the value will always just be '045'
                moves = TURNING_RADIUS / CELL_LENGTH;   // floor div. of turning radius against cell len
                // backward movement
                for(int i = 0; i < moves - 1; i++) {
                    gridMap.moveRobot("back");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                //back-left movement
                gridMap.moveRobot("backleft");
                try {
                    Thread.sleep(MILLI_DELAY);
                } catch(InterruptedException e) {
                    showLog("InterruptedException occurred when calling Thread.sleep()!");
                    e.printStackTrace();
                }
                // backward movement
                for(int i = 0; i < moves - 2; i++) {
                    gridMap.moveRobot("back");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                break;
            case 'e':   // 90 deg back-right - I assume that the value will always just be '045'
                moves = TURNING_RADIUS / CELL_LENGTH;   // floor div. of turning radius against cell len
                // backward movement
                for(int i = 0; i < moves - 1; i++) {
                    gridMap.moveRobot("back");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                //back-right movement
                gridMap.moveRobot("backright");
                try {
                    Thread.sleep(MILLI_DELAY);
                } catch(InterruptedException e) {
                    showLog("InterruptedException occurred when calling Thread.sleep()!");
                    e.printStackTrace();
                }
                // backward movement
                for(int i = 0; i < moves - 2; i++) {
                    gridMap.moveRobot("back");
                    try {
                        Thread.sleep(MILLI_DELAY);
                    } catch(InterruptedException e) {
                        showLog("InterruptedException occurred when calling Thread.sleep()!");
                        e.printStackTrace();
                    }
                }
                break;
            default:
                showLog("Invalid commandType!");
        }
        showLog("Exited translatePath");
    }

    private static void showLog(String message) {
        Log.d(TAG, message);
    }
}

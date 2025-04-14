// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class WristConstants{
    public static final double rotationP = 0.015;
    public static final double rotationI = 0.000002;
    public static final double rotationD = 4;

    public static final double rotationOffset=183.7;
    public static final double zeroPosition=202.6;
  }

  public static class BoardConstants {
    public static final int boardMotorID = 2;
    public static final int boardMotor2ID = 3;
    public static final int limitSwitchPort=0;
    public static final int lightsID=10;
  }

}

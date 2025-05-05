// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Board;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.ExampleSubsystem;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  private final CommandPS5Controller PS5Controller = new CommandPS5Controller(OperatorConstants.kDriverControllerPort);
  private final Board board = new Board();
  private final Pneumatics pneumatics = new Pneumatics();

  public RobotContainer() {
    configureBindings();
  }


  private void configureBindings() {
    //manual rotation
    PS5Controller.square().
    whileTrue(new InstantCommand(() -> board.spinMotor())).
    whileFalse(new InstantCommand(() -> board.stopMotor()));

    PS5Controller.triangle().
    whileTrue(new InstantCommand(() -> board.reverseMotor())).
    whileFalse(new InstantCommand(() -> board.stopMotor()));

    //PS5Controller.circle().
    //whileTrue(new InstantCommand(() -> board.spinMotor2())).
    //whileFalse(new InstantCommand(() -> board.stopMotor2()));

    //setpoints
    PS5Controller.povUp().
    onTrue(new InstantCommand(() -> board.setAngle(80)));

    PS5Controller.povRight().
    onTrue(new InstantCommand(() -> board.setAngle(60)));

    PS5Controller.povLeft().
    onTrue(new InstantCommand(() -> board.setAngle(40)));

    PS5Controller.povDown().
    onTrue(new InstantCommand(() -> board.setAngle(20)));

    //LED lights
    PS5Controller.L1().whileTrue(new InstantCommand(() -> board.greenBlinkin())).
    whileFalse(new InstantCommand(() -> board.greenBlinkin()));

    //Pneumatics
    PS5Controller.circle()
    .whileTrue(new InstantCommand(() -> pneumatics.toggleSolenoidTrue()))
    .whileFalse(new InstantCommand(() -> pneumatics.toggleSolenoidFalse()));
  }

  
}
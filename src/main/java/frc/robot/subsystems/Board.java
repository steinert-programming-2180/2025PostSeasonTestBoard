package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController.ArbFFUnits;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BoardConstants;
import frc.robot.Constants.WristConstants;

public class Board extends SubsystemBase{
    //objects created here

    double motorSpeed;
    DigitalInput limitSwitch;
    SparkMax boardMotor;
    SparkMaxConfig boardMotorConfig;
    SparkMax boardMotor2;
    SparkMaxConfig boardMotor2Config;
    double desiredAngle;
    private Spark m_blinkinSpark;
    

    public Board(){
    //objects defined here
    m_blinkinSpark = new Spark(BoardConstants.lightsID);
    boardMotor = new SparkMax(BoardConstants.boardMotorID, MotorType.kBrushless);
    boardMotorConfig=new SparkMaxConfig();
    

    boardMotor2 = new SparkMax(BoardConstants.boardMotor2ID, MotorType.kBrushless);
    boardMotor2Config=new SparkMaxConfig();

    limitSwitch=new DigitalInput(BoardConstants.limitSwitchPort);
    coinfigMotors();
    }

    private void coinfigMotors(){
        boardMotorConfig.
        inverted(true).
        idleMode(IdleMode.kBrake).
        closedLoopRampRate(0.25);

        boardMotorConfig.absoluteEncoder.
        positionConversionFactor(360).
        velocityConversionFactor(360)
        .zeroOffset(0.4)
        .inverted(true);

        boardMotorConfig.closedLoop.
        feedbackSensor(FeedbackSensor.kAbsoluteEncoder).
        pid(WristConstants.rotationP, WristConstants.rotationI, WristConstants.rotationD)
        // p(WristConstants.rotationP).
        // i(WristConstants.rotationI).
        // d(WristConstants.rotationD)
        ;

        boardMotor2Config.
        inverted(false).
        idleMode(IdleMode.kBrake);

        boardMotor.configure(boardMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        boardMotor2.configure(boardMotor2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    
    public void spinMotor(){
        boardMotor.set(0.3);
    }

    public void reverseMotor(){
        boardMotor.set(-0.3);
    }

    public void spinMotor2(){
        boardMotor2.set(0.3);
    }

    public void stopMotor(){
        boardMotor.set(0);
    }

    public void greenBlinkin() {
        // m_blinkinSpark.set(0);
        m_blinkinSpark.set(0.37);
        
    }

    public void stopMotor2(){
        boardMotor2.set(0);
    }

    public void setAngle(double angle){
        // boardMotor.getClosedLoopController().setReference(angle+WristConstants.rotationOffset,
        // ControlType.kPosition, ClosedLoopSlot.kSlot0);

        boardMotor.getClosedLoopController().setReference(
        //PID
        angle+WristConstants.rotationOffset, ControlType.kPosition, ClosedLoopSlot.kSlot0,
        //FeedForward
        0.02*Math.cos(WristConstants.zeroPosition-getAngle()), ArbFFUnits.kPercentOut);
        desiredAngle=angle;
    }

    private double getAngle(){
        return boardMotor.getAbsoluteEncoder().getPosition();
    }
    
    @Override
    public void periodic(){
        motorSpeed=boardMotor.get();
        if(limitSwitch.get()&&motorSpeed<0){
            stopMotor();
        }
        SmartDashboard.putNumber("Speed", motorSpeed);
        SmartDashboard.putBoolean("Limit Switch", limitSwitch.get());
        SmartDashboard.putNumber("distance", 
        Math.abs(getAngle()-(desiredAngle+WristConstants.rotationOffset)));
        SmartDashboard.putNumber("PID Target", desiredAngle+WristConstants.rotationOffset);
    }
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BoardConstants;

public class Pneumatics extends SubsystemBase {
    Solenoid m_solenoid;
    Compressor compressor;
    
    public Pneumatics(){
        m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, BoardConstants.pneumaticsID);
        compressor=new Compressor(PneumaticsModuleType.REVPH);
        compressor.enableDigital();
    }

    public void toggleSolenoidTrue(){
        m_solenoid.set(true);
    }

    public void toggleSolenoidFalse(){
        m_solenoid.set(false);
    }
 

}

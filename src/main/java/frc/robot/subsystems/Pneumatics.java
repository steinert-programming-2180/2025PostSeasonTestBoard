package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BoardConstants;

public class Pneumatics extends SubsystemBase {
    Solenoid m_solonoid;
    private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, 0);

    
    public Pneumatics(){
        Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, BoardConstants.pneumaticsID);
    }

    public void toggleSolenoidTrue(){
        m_solenoid.set(true);
    }

    public void toggleSolenoidFalse(){
        m_solenoid.set(false);
    }
 

}

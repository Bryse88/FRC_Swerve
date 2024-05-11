// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;


/** Add your docs here. */
public class Pneumatics extends SubsystemBase{
    private static final int PH_CAN_ID = 31;

    private boolean firstArm = false;
    private boolean secondArm = false;
   
    
    PneumaticHub m_pH = new PneumaticHub(PH_CAN_ID);
    DoubleSolenoid m_firstarmSolenoid = m_pH.makeDoubleSolenoid(9,8);
    DoubleSolenoid m_secondArmSolenoid = m_pH.makeDoubleSolenoid(14,15);
    //DoubleSolenoid m_intakeSolenoid = m_pH.makeDoubleSolenoid(0,0);


   //12 is arm up
   //13 is arm down
   //14 is wrist down
   //15 is wrist up


    //private DoubleSolenoid solenoid;

   //PneumaticHub m_ph = new PneumaticHub(31);

    public Pneumatics(){
        //solenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 14, 15);
        //m_ph.enableCompressorDigital();
        
     }
     
     public boolean firstArm() {
         return this.firstArm;
     }
     public boolean secondArm() {
        return this.secondArm;
     }
     public void setFirstArmForward(){
      //forward channal. 12
        m_firstarmSolenoid.set(DoubleSolenoid.Value.kForward);
        System.out.println("kForward:"+m_firstarmSolenoid.get());
        firstArm = true;
     }
     public void setFirstArmDown(){
      //Reverse Channal. 13
        m_firstarmSolenoid.set(DoubleSolenoid.Value.kReverse);
        System.out.println("kReverse:"+m_firstarmSolenoid.get());
        firstArm = false;
     }
     public void setSecondArmForward(){
      //Forward Channal 15
        m_secondArmSolenoid.set(DoubleSolenoid.Value.kForward);
        System.out.println("kForward:"+m_secondArmSolenoid.get());
        secondArm = true;
     }
     public void setSecondArmDown(){
      //Reverse Channal. 14. 
        m_secondArmSolenoid.set(DoubleSolenoid.Value.kReverse);
        System.out.println("kReverse:"+m_secondArmSolenoid.get());
        secondArm = false;
     } 
}
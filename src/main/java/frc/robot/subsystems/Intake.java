
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.GenericEntry;



/** Add your docs here. */
public class Intake extends SubsystemBase{
    private CANSparkMax m_intake1Motor;
    private CANSparkMax m_intake2Motor;

   //Shuffleboard
   GenericEntry encoderValue;
   GenericEntry encoderValue2;

    public Intake(){
      m_intake1Motor = new CANSparkMax(9, MotorType.kBrushless);
      m_intake1Motor.restoreFactoryDefaults();
      m_intake1Motor.setIdleMode(IdleMode.kBrake);
      m_intake2Motor = new CANSparkMax(11, MotorType.kBrushless);
      m_intake2Motor.restoreFactoryDefaults();
      m_intake2Motor.setIdleMode(IdleMode.kBrake);

      //m_elevatorMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
      //_elevatorMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);


      //m_elevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward,2);
      //m_elevatorMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse,0);
       
  
     }
   


     @Override
     public void periodic() {
        //This starts when ro
  

        
        
     }

   public void setMotorIntake1() { // Motor 9
        m_intake1Motor.set(-0.35);
        System.out.print("motor1 ");
   }
   public void setMotorOutake1() { // Motor 9
        m_intake1Motor.set(0.55);
   }

   public void setMotorIntake2() { //Motor 11
      m_intake2Motor.set(0.35);
    }   

    public void setmotorOutake2() { // Motor 11
    m_intake2Motor.set(-0.55);
    }

    /*public void setMotor2stop() {
      m_intake2Motor.set(0);
    }

    public void setMotor1stop() {
        m_intake2Motor.set(0);
    }*/

    public void intakeStop() {
      if(m_intake1Motor.get()<-0.01) {
        m_intake1Motor.set(0);
        m_intake2Motor.set(0);
      }
    }
    
    public void outakeStop() {

      if(m_intake1Motor.get()>0.01) {
        m_intake1Motor.set(0);
        m_intake2Motor.set(0);
      }
    }
  }